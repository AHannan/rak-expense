package com.rak.expense.dao.model.expense;

import com.rak.expense.dao.model.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@MappedSuperclass
@Getter
@Setter
@Table(name = "expense", schema = "expense")
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
