package com.rak.expense.service.expense;

import com.rak.expense.dao.model.expense.Expense;
import com.rak.expense.dao.repository.expense.ExpenseRepository;
import com.rak.expense.service.client.budget.BudgetServiceClient;
import com.rak.expense.service.client.budget.dto.BudgetViewDto;
import com.rak.expense.service.client.notification.NotificationServiceClient;
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

    @Async
    public void notifyIfBudgetExceeded(Expense expense) {
        BigDecimal totalExpenses = repository.findTotalExpensesByBudgetCategoryIdAndUserId(expense.getBudgetCategoryId(), expense.getUserId());
        BudgetViewDto budget = budgetServiceClient.getBudgetByCategoryIdAndUserId(expense.getBudgetCategoryId(), expense.getUserId());
        if (totalExpenses.compareTo(budget.getAmount()) > 0) {
            notificationServiceClient.sendNotification(expense.getUserId(), toDescription(expense, budget, totalExpenses));
        }
    }

    private String toDescription(Expense expense, BudgetViewDto budget, BigDecimal totalExpenses) {
        return new StringBuilder()
                .append("Budget for category: " + budget.getCategory().getName())
                .append(" ").append("with limit: " + budget.getAmount() + " exceeded by " + totalExpenses.subtract(budget.getAmount()))
                .append(" ").append("after expense with description: " + expense.getDescription())
                .append(" ").append("and amount" + expense.getAmount())
                .toString();
    }

}
