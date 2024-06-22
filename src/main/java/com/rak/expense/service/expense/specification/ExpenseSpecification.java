package com.rak.expense.service.expense.specification;

import com.rak.expense.dao.model.expense.Expense;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ExpenseSpecification {

    public static Specification<Expense> filterByUserId(String userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), userId);
    }

    public static Specification<Expense> filterByCategoryId(String categoryId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("budgetCategoryId"), categoryId);
    }

    public static Specification<Expense> buildSpecification(String userId, String budgetCategoryId) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (userId != null) {
                predicates.add(filterByUserId(userId).toPredicate(root, query, criteriaBuilder));
            }
            if (budgetCategoryId != null) {
                predicates.add(filterByCategoryId(budgetCategoryId).toPredicate(root, query, criteriaBuilder));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
