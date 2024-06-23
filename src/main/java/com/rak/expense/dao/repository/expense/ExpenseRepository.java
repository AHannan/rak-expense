package com.rak.expense.dao.repository.expense;

import com.rak.expense.dao.model.expense.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, String>, JpaSpecificationExecutor<Expense> {
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.budgetCategoryId = :budgetCategoryId AND e.userId = :userId")
    BigDecimal findTotalExpensesByBudgetCategoryIdAndUserId(String budgetCategoryId, String userId);
}
