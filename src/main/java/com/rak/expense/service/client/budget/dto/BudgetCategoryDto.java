package com.rak.expense.service.client.budget.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BudgetCategoryDto {
    private String id;
    private String name;
}
