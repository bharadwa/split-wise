package org.split.splitwise.strategies;

import org.split.splitwise.dtos.Pair;
import org.split.splitwise.models.Expense;
import org.split.splitwise.models.User;
import org.split.splitwise.models.UserExpense;
import org.split.splitwise.models.UserExpenseType;
import org.split.splitwise.repositories.UserExpenseRepository;
import org.split.splitwise.services.dto.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.lang.Math.abs;

@Component
@Qualifier("greedy")
public class GreedySettleUpStrategy implements SettleUpStrategy {


    private final UserExpenseRepository userExpenseRepository;

    @Autowired
    public GreedySettleUpStrategy(UserExpenseRepository userExpenseRepository) {
        this.userExpenseRepository = userExpenseRepository;
    }

    @Override
    public List<Transaction> settleUpExpenses(List<Expense> expenses) {

        //we can use two priority queues to store the users with max credit and max debit
        List<UserExpense> userExpenses = userExpenseRepository.findAllByExpenseIn(expenses);
        Map<User, Integer> paidExpenses = new HashMap<>();
        userExpenses.forEach(userExpense -> {
            int amountToPaid = 0;
            if (paidExpenses.containsKey(userExpense.getUser())) {
                amountToPaid = userExpense.getAmount();
            }
            if (userExpense.getUserExpenseType() == UserExpenseType.PAID) {
                paidExpenses.put(userExpense.getUser(), amountToPaid + userExpense.getAmount());
            } else {
                paidExpenses.put(userExpense.getUser(), amountToPaid - userExpense.getAmount());
            }
        });


        //max heap
        PriorityQueue<Pair<User, Integer>> whoPaid = new PriorityQueue<>(
                Comparator.comparing(Pair::getSecond, Collections.reverseOrder())
        );

        //min heap
        PriorityQueue<Pair<User, Integer>> whoHasToPay = new PriorityQueue<>(
                Comparator.comparingInt((Pair<User, Integer> pair) -> Math.abs(pair.getSecond()))
        );

        for (Map.Entry<User, Integer> expense : paidExpenses.entrySet()) {
            if (expense.getValue() < 0) {
                whoHasToPay.add(new Pair<User, Integer>(expense.getKey(), expense.getValue()));
            } else {
                whoPaid.add(new Pair<User, Integer>(expense.getKey(), expense.getValue()));
            }
        }
        List<Transaction> transactions = new ArrayList<>();

        while (!whoHasToPay.isEmpty() && !whoPaid.isEmpty()) {
            Pair<User, Integer> whoOws = whoHasToPay.poll();
            Pair<User, Integer> whoOwned = whoPaid.poll();
            Transaction transaction = new Transaction();
            int amount = 0;
            assert whoOwned != null;
            if (abs(whoOws.getSecond()) < whoOwned.getSecond()) {
                amount = whoOws.getSecond();
                transaction.setFromUser(whoOws.getFirst());
                transaction.setToUser(whoOwned.getFirst());
                if (!whoOws.getSecond().equals(whoOwned.getSecond())) {
                    whoPaid.add(new Pair<>(whoOwned.getFirst(), whoOwned.getSecond() - abs(whoOws.getSecond())));
                }
            } else {
                amount = whoOwned.getSecond();
                transaction.setFromUser(whoOws.getFirst());
                transaction.setToUser(whoOwned.getFirst());
                if (!whoOws.getSecond().equals(whoOwned.getSecond())) {
                    whoPaid.add(new Pair<>(whoOws.getFirst(), whoOwned.getSecond() + whoOws.getSecond()));
                }

            }
            transactions.add(transaction);
        }
        return transactions;
    }


}
