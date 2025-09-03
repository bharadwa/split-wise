package org.split.splitwise.repositories;

import org.split.splitwise.models.Expense;
import org.split.splitwise.models.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {


    List<Expense> findAllByGroup(Groups group);
}
