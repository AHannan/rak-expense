package com.rak.expense.dao.model.expense;

import com.rak.expense.dao.model.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "expense")
public class Expense extends AbstractEntity {

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String budgetCategoryId;

    @Column(nullable = false)
    private String userId;

}
