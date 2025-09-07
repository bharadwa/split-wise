package org.split.splitwise.strategies;

import org.split.splitwise.models.Expense;
import org.split.splitwise.models.ExpenseType;
import org.split.splitwise.services.dto.Transaction;
import org.split.splitwise.models.UserExpense;
import org.split.splitwise.models.UserExpenseType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Qualifier("greedy")
public class GreedySettleUpStrategy implements  SettleUpStrategy{
    @Override
    public List<Transaction> settleUpGroup(List<Expense> expenses) {

        //we can use two priority queues to store the users with max credit and max debit
        List<Expense> unPaidExpenses = expenses.stream().filter(expense -> expense.getExpenseType() == ExpenseType.EXPENSE).toList();
        // from the list of unpaid expenses we can create a map of user to amount owed
        List<UserExpense> unpaidUsers = unPaidExpenses.stream()
                .flatMap(user -> user.getPaidByUsers().stream())
                .collect(Collectors.toList());
        List<UserExpense> paideUsers =unPaidExpenses.stream()
                        .flatMap(user-> user.getPaidForUsers().stream())
                        .collect(Collectors.toList());
        Map<Long,UserExpense> unPaidExpenseMap=new HashMap<>();
        unpaidUsers.stream().forEach(expense -> {
            if(!unPaidExpenseMap.containsKey(expense.getUser().getId())) {
                unPaidExpenseMap.put(expense.getUser().getId(), expense);
            }else {
                UserExpense userExpense=unPaidExpenseMap.get(expense.getUser().getId());
                userExpense.setAmount(userExpense.getAmount()+expense.getAmount());
                unPaidExpenseMap.put(expense.getUser().getId(),userExpense);
            }
        });

        Map<Long,UserExpense> paidUserMap=new HashMap<>();
        paideUsers.stream().forEach(expense -> {
            if(!paidUserMap.containsKey(expense.getUser().getId())) {
                paidUserMap.put(expense.getUser().getId(), expense);
            }else {
                paidUserMap.get(expense.getUser().getId()).setAmount(paidUserMap.get(expense.getUser().getId()).getAmount()+expense.getAmount());
            }
        });

        PriorityQueue<UserExpense> unpaidExpenses=new PriorityQueue<>(Comparator.comparing(UserExpense::getAmount,Collections.reverseOrder()));
        PriorityQueue<UserExpense> paidUsers=new PriorityQueue<>(Comparator.comparing(UserExpense::getAmount,Collections.reverseOrder()));

        List<Transaction> unpaidTransactions=new ArrayList<>();
        while(!unpaidExpenses.isEmpty()&&!paidUsers.isEmpty()){

        }
        return unpaidTransactions;
    }


}
