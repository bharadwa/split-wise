package org.split.splitwise.services;

import org.split.splitwise.exceptions.GroupNotFoundException;
import org.split.splitwise.models.Expense;
import org.split.splitwise.models.Groups;
import org.split.splitwise.services.dto.Transaction;
import org.split.splitwise.repositories.ExpenseRepository;
import org.split.splitwise.repositories.GroupRepository;
import org.split.splitwise.repositories.UserExpenseRepository;
import org.split.splitwise.repositories.UserRepository;
import org.split.splitwise.strategies.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SettleUpGroupServiceImpl implements SettleUpGroupService{


    private final GroupRepository groupRepository;

    private final UserExpenseRepository userExpenseRepository;

    private final UserRepository userRepository;

    private final ExpenseRepository expenseRepository;

    private final SettleUpStrategy settleUpStrategy;

    @Autowired
    public SettleUpGroupServiceImpl(GroupRepository groupRepository,
                                    UserExpenseRepository userExpenseRepository,
                                    UserRepository userRepository,
                                    ExpenseRepository expenseRepository,
                                    @Qualifier("greedy") SettleUpStrategy  settleUpStrategy) {
        this.groupRepository = groupRepository;
        this.userExpenseRepository = userExpenseRepository;
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
        this.settleUpStrategy=settleUpStrategy;
    }

    @Transactional
    @Override
    public List<Transaction> settleUpGroup(long groupId) throws GroupNotFoundException {
         Groups group=this.groupRepository.findById(groupId).orElseThrow( ()-> new GroupNotFoundException("Group not found") );
         List<Expense> expenses=this.expenseRepository.findAllByGroup(group);
         return this.settleUpStrategy.settleUpGroup(expenses);
    }
}
