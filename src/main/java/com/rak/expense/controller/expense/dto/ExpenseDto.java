package com.rak.expense.controller.expense.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ExpenseDto {
    private String id;
    private String description;
    private BigDecimal amount;
    private String budgetCategoryId;
    private String userId;
}
