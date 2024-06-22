package com.rak.expense.service.expense.client.budget;

import com.rak.expense.service.expense.client.budget.dto.BudgetViewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class BudgetServiceClient {

    private final RestTemplate restTemplate;

    @Value("${service.budget.url}")
    String budgetServiceBaseUrl;

    public BudgetViewDto getBudgetById(Long budgetId) {
        return restTemplate.getForObject(budgetServiceBaseUrl + "/" + budgetId, BudgetViewDto.class);
    }
}
