package org.split.splitwise.services;

import org.split.splitwise.exceptions.GroupNotFoundException;
import org.split.splitwise.services.dto.Transaction;

import java.util.List;

public interface SettleUpGroupService {

    List<Transaction> settleUpGroup(long groupId) throws GroupNotFoundException;
}
