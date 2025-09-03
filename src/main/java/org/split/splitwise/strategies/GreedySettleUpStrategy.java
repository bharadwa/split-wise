package org.split.splitwise.strategies;

import org.split.splitwise.models.Expense;
import org.split.splitwise.models.Transaction;
import org.split.splitwise.models.UserExpense;
import org.split.splitwise.models.UserExpenseType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Qualifier("greedy")
public class GreedySettleUpStrategy implements  SettleUpStrategy{
    @Override
    public List<Transaction> settleUpGroup(List<Expense> expenses) {

        //we can use two priority queues to store the users with max credit and max debit

        List<Expense> unPaidExpenses = expenses.stream().filter(expense -> expense.getExpenseType() == UserExpenseType.EXPENSE).toList();

        // from the list of unpaid expenses we can create a map of user to amount owed

        List<UserExpense> unpaidUsers= unPaidExpenses.stream().flatMap(user -> user.getPaidByUsers().stream()).collect(Collectors.groupingBy(user ->user.g));
        List<UserExpense> paidUsers= unPaidExpenses.stream().flatMap(user -> user.getPaidForUsers().stream()).collect(Collectors.toList());
        return List.of();
    }
}
