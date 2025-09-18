package org.split.splitwise.strategies;

import org.split.splitwise.models.Expense;
import org.split.splitwise.services.dto.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("random")
public class RandomSettleUpStrategy implements SettleUpStrategy {

    @Override
    public List<Transaction> settleUpExpenses(List<Expense> expenses) {
        return List.of();
    }
}
