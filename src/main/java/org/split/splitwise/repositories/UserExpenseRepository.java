package org.split.splitwise.repositories;

import org.split.splitwise.models.Expense;
import org.split.splitwise.models.Group;
import org.split.splitwise.models.User;
import org.split.splitwise.models.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserExpenseRepository extends JpaRepository<UserExpense ,Long> {


    List<UserExpense> findAllByUser(User user);

    List<UserExpense> findAllByExpenseIn(Collection<Expense> expenses);

}
