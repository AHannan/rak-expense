package com.rak.expense.service.expense;

import com.rak.expense.dao.model.expense.Expense;
import com.rak.expense.dao.repository.expense.ExpenseRepository;
import com.rak.expense.service.client.budget.BudgetServiceClient;
import com.rak.expense.service.client.budget.dto.BudgetViewDto;
import com.rak.expense.service.client.notification.NotificationServiceClient;
import com.rak.expense.service.client.notification.mapper.NotificationDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ExpenseNotificationService {

    private final ExpenseRepository repository;
    private final BudgetServiceClient budgetServiceClient;
    private final NotificationServiceClient notificationServiceClient;
    private final NotificationDtoMapper notificationDtoMapper;

    @Async
    public void notifyIfBudgetExceeded(Expense expense) {
        var totalExpenses = repository.findTotalExpensesByBudgetCategoryIdAndUserId(expense.getBudgetCategoryId(), expense.getUserId());
        var budget = budgetServiceClient.getBudgetByCategoryIdAndUserId(expense.getBudgetCategoryId(), expense.getUserId());
        if (totalExpenses.compareTo(budget.getAmount()) > 0) {
            var notificationDto = notificationDtoMapper.map(expense.getUserId(), toMessage(expense, budget, totalExpenses));
            notificationServiceClient.sendNotification(notificationDto);
        }
    }

    private String toMessage(Expense latestExpense,
                             BudgetViewDto budget,
                             BigDecimal totalExpenses) {
        String budgetCategoryName = budget.getCategory().getName();
        BigDecimal budgetLimit = budget.getAmount();
        BigDecimal exceededAmount = totalExpenses.subtract(budgetLimit);
        BigDecimal latestExpenseAmount = latestExpense.getAmount();
        String latestExpenseDescription = latestExpense.getDescription();
        return toMessage(budgetCategoryName, budgetLimit, exceededAmount, latestExpenseDescription, latestExpenseAmount);
    }

    // TODO: Use Mustache for templating in notification service
    private static String toMessage(String budgetCategoryName,
                                    BigDecimal budgetLimit,
                                    BigDecimal exceededAmount,
                                    String latestExpenseDescription,
                                    BigDecimal latestExpenseAmount) {
        return "Budget for category: " + budgetCategoryName +
                " with limit: " + budgetLimit + " exceeded by " + exceededAmount +
                ". Latest Expense was: " + latestExpenseDescription +
                " and amount" + latestExpenseAmount;
    }

}
