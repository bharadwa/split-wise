package org.split.splitwise.services;

import org.split.splitwise.exceptions.GroupNotFoundException;
import org.split.splitwise.models.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettleUpGroupServiceImpl implements SettleUpGroupService{


    @Override
    public List<Transaction> settleUpGroup(long group) throws GroupNotFoundException {
        return List.of();
    }
}
