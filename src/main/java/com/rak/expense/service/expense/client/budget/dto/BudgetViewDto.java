package com.rak.expense.service.expense.client.budget.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BudgetViewDto {
    private String id;
    private String userId;
    private BigDecimal amount;
    private BudgetCategoryDto category;
}
