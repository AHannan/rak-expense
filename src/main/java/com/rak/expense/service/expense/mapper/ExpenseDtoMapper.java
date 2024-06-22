package com.rak.expense.service.expense.mapper;

import com.rak.expense.controller.expense.dto.ExpenseDto;
import com.rak.expense.dao.model.expense.Expense;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExpenseDtoMapper {

    public ExpenseDto map(Expense entity) {

        return ExpenseDto.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .amount(entity.getAmount())
                .budgetCategoryId(entity.getBudgetCategoryId())
                .userId(entity.getUserId())
                .build();
    }
}
