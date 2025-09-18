package org.split.splitwise.services;

import org.split.splitwise.exceptions.GroupNotFoundException;
import org.split.splitwise.exceptions.UserNotFoundException;
import org.split.splitwise.services.dto.Transaction;

import java.util.List;

public interface SettleUpService {

    List<Transaction> settleUpGroup(long groupId) throws GroupNotFoundException;

    List<Transaction> settleUpUser(long userId) throws UserNotFoundException;
}
