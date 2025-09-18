package org.split.splitwise.strategies;

import org.split.splitwise.models.Expense;
import org.split.splitwise.services.dto.Transaction;

import java.util.List;

public interface SettleUpStrategy {

    public List<Transaction> settleUpExpenses(List<Expense> expenses);
}
