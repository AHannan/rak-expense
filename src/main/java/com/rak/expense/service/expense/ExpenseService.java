package com.rak.expense.service.expense;

import com.rak.expense.controller.expense.dto.ExpenseDto;
import com.rak.expense.dao.model.expense.Expense;
import com.rak.expense.dao.repository.expense.ExpenseRepository;
import com.rak.expense.service.expense.mapper.ExpenseDtoMapper;
import com.rak.expense.service.expense.specification.ExpenseSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository repository;
    private final ExpenseDtoMapper mapper;

    public Page<ExpenseDto> getAll(String userId, String budgetCategoryId, Pageable pageable) {
        return repository.findAll(ExpenseSpecification.buildSpecification(userId, budgetCategoryId), pageable)
                .map(mapper::map);
    }

    public Optional<ExpenseDto> getById(String id) {
        return repository.findById(id)
                .map(mapper::map);
    }

    public ExpenseDto create(ExpenseDto dto) {
        var result = new Expense();
        result.setUserId(dto.getUserId());
        result.setDescription(dto.getDescription());
        result.setAmount(dto.getAmount());
        result.setBudgetCategoryId(dto.getBudgetCategoryId());

        return mapper.map(repository.save(result));
    }

    public Optional<ExpenseDto> update(String id, ExpenseDto dto) {
        return repository.findById(id).map(existing -> {
            existing.setAmount(dto.getAmount());
            existing.setBudgetCategoryId(dto.getBudgetCategoryId());
            return mapper.map(repository.save(existing));
        });
    }

    public boolean delete(String id) {
        var result = repository.findById(id);
        if (result.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
