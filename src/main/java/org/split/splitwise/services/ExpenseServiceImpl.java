package org.split.splitwise.services;

import org.split.splitwise.exceptions.GroupNotFoundException;
import org.split.splitwise.exceptions.UserNotFoundException;
import org.split.splitwise.models.Expense;
import org.split.splitwise.repositories.ExpenseRepository;
import org.split.splitwise.repositories.GroupRepository;
import org.split.splitwise.repositories.UserExpenseRepository;
import org.split.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final UserRepository userRepository;

    private final GroupRepository groupRepository;

    private final UserExpenseRepository userExpenseRepository;

    @Autowired
    public ExpenseServiceImpl(UserRepository userRepository, GroupRepository groupRepository,
                              ExpenseRepository expenseRepository, UserExpenseRepository userExpenseRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
        this.userExpenseRepository = userExpenseRepository;
    }

    @Override
    public Expense addExpense(long createdUserId, long groupId, List<Pair> whoPaid, List<Pair> whoHasToPay) throws UserNotFoundException, GroupNotFoundException {
        return null;
    }
}
