package com.rak.expense.service.client.budget.dto;

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
