package org.split.splitwise.services;

import org.split.splitwise.exceptions.GroupNotFoundException;
import org.split.splitwise.exceptions.UserNotFoundException;
import org.split.splitwise.models.Expense;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ExpenseService {

    Expense  addExpense(long createdUserId, long groupId, List<Pair> whoPaid, List<Pair> whoHasToPay) throws UserNotFoundException, GroupNotFoundException;
}
