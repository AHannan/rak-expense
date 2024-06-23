package com.rak.expense.service.client.budget;

import com.rak.expense.service.client.budget.dto.BudgetViewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class BudgetServiceClient {

    // TODO: Read from Properties
    private String SERVICE_BUDGET_BASE_URL = "http://localhost:8080/api/budget";

    private final RestTemplate restTemplate;

    public BudgetViewDto getBudgetByCategoryIdAndUserId(String categoryId, String userId) {
        return restTemplate.getForObject(SERVICE_BUDGET_BASE_URL + "/budgets/category-id/" + categoryId + "/userId/" + userId, BudgetViewDto.class);
    }
}
