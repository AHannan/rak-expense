package com.rak.expense.service.expense;

import com.rak.expense.controller.expense.dto.ExpenseDto;
import com.rak.expense.dao.model.expense.Expense;
import com.rak.expense.dao.repository.expense.ExpenseRepository;
import com.rak.expense.service.expense.mapper.ExpenseDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ExpenseServiceTest {

    @Mock
    private ExpenseRepository repository;

    @Mock
    private ExpenseDtoMapper mapper;

    @Mock
    private ExpenseNotificationService expenseNotificationService;

    @InjectMocks
    private ExpenseService service;

    private Expense expense;
    private ExpenseDto expenseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        expense = new Expense();
        expense.setId("1");
        expense.setUserId("user1");
        expense.setDescription("Test expense");
        expense.setAmount(BigDecimal.valueOf(100));
        expense.setBudgetCategoryId("budgetCategory1");

        expenseDto = ExpenseDto.builder()
                .id("1")
                .userId("user1")
                .description("Test expense")
                .amount(BigDecimal.valueOf(100))
                .budgetCategoryId("budgetCategory1")
                .build();
    }

    @Test
    void testGetAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Expense> page = new PageImpl<>(Collections.singletonList(expense), pageable, 1);

        when(repository.findAll(any(Specification.class), eq(pageable))).thenReturn(page);
        when(mapper.map(expense)).thenReturn(expenseDto);

        Page<ExpenseDto> result = service.getAll("user1", "budgetCategory1", pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Test expense", result.getContent().get(0).getDescription());

        verify(repository, times(1)).findAll(any(Specification.class), eq(pageable));
        verify(mapper, times(1)).map(expense);
    }

    @Test
    void testGetById() {
        when(repository.findById("1")).thenReturn(Optional.of(expense));
        when(mapper.map(expense)).thenReturn(expenseDto);

        Optional<ExpenseDto> result = service.getById("1");

        assertTrue(result.isPresent());
        assertEquals("Test expense", result.get().getDescription());

        verify(repository, times(1)).findById("1");
        verify(mapper, times(1)).map(expense);
    }

    @Test
    void testCreate() {
        when(repository.save(any(Expense.class))).thenReturn(expense);
        when(mapper.map(expense)).thenReturn(expenseDto);

        ExpenseDto result = service.create(expenseDto);

        assertNotNull(result);
        assertEquals("user1", result.getUserId());
        assertEquals("Test expense", result.getDescription());
        assertEquals(BigDecimal.valueOf(100), result.getAmount());

        verify(repository, times(1)).save(any(Expense.class));
        verify(mapper, times(1)).map(expense);
        verify(expenseNotificationService, times(1)).notifyIfBudgetExceeded(expense);
    }

    @Test
    void testUpdate() {
        when(repository.findById("1")).thenReturn(Optional.of(expense));
        when(repository.save(any(Expense.class))).thenReturn(expense);
        when(mapper.map(expense)).thenReturn(expenseDto);

        Optional<ExpenseDto> result = service.update("1", expenseDto);

        assertTrue(result.isPresent());
        assertEquals("Test expense", result.get().getDescription());

        verify(repository, times(1)).findById("1");
        verify(repository, times(1)).save(any(Expense.class));
        verify(mapper, times(1)).map(expense);
        verify(expenseNotificationService, times(1)).notifyIfBudgetExceeded(expense);
    }

    @Test
    void testDelete() {
        when(repository.findById("1")).thenReturn(Optional.of(expense));

        boolean result = service.delete("1");

        assertTrue(result);

        verify(repository, times(1)).findById("1");
        verify(repository, times(1)).deleteById("1");
    }

    @Test
    void testDelete_NotFound() {
        when(repository.findById("1")).thenReturn(Optional.empty());

        boolean result = service.delete("1");

        assertFalse(result);

        verify(repository, times(1)).findById("1");
        verify(repository, never()).deleteById("1");
    }
}
