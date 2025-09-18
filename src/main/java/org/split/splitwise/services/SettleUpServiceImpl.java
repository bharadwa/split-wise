package org.split.splitwise.services;

import org.split.splitwise.exceptions.GroupNotFoundException;
import org.split.splitwise.exceptions.UserNotFoundException;
import org.split.splitwise.models.Expense;
import org.split.splitwise.models.Group;
import org.split.splitwise.models.User;
import org.split.splitwise.models.UserExpense;
import org.split.splitwise.repositories.ExpenseRepository;
import org.split.splitwise.repositories.GroupRepository;
import org.split.splitwise.repositories.UserExpenseRepository;
import org.split.splitwise.repositories.UserRepository;
import org.split.splitwise.services.dto.Transaction;
import org.split.splitwise.strategies.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SettleUpServiceImpl implements SettleUpService {


    private final GroupRepository groupRepository;

    private final UserExpenseRepository userExpenseRepository;

    private final UserRepository userRepository;

    private final ExpenseRepository expenseRepository;

    private final SettleUpStrategy settleUpStrategy;

    @Autowired
    public SettleUpServiceImpl(GroupRepository groupRepository,
                               UserExpenseRepository userExpenseRepository,
                               UserRepository userRepository,
                               ExpenseRepository expenseRepository,
                               @Qualifier("greedy") SettleUpStrategy settleUpStrategy) {
        this.groupRepository = groupRepository;
        this.userExpenseRepository = userExpenseRepository;
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
        this.settleUpStrategy = settleUpStrategy;
    }

    //@Transactional
    @Override
    public List<Transaction> settleUpGroup(long groupId) throws GroupNotFoundException {
        Group group = this.groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException("Group not found"));
        List<Expense> expenses = this.expenseRepository.findAllByGroup(group);
        return this.settleUpStrategy.settleUpExpenses(expenses);
    }

    @Override
    public List<Transaction> settleUpUser(long userId) throws UserNotFoundException {
        // Implementation for settling up a user
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        List<UserExpense> userExpenses = this.userExpenseRepository.findAllByUser(user);
        List<Expense> expenses = userExpenses.stream().map(UserExpense::getExpense).collect(Collectors.toList());
        return this.settleUpStrategy.settleUpExpenses(expenses).stream().filter(transaction -> transaction.getToUser().getId() == user.getId() || transaction.getFromUser().getId() == user.getId()).collect(Collectors.toList());
    }
}
