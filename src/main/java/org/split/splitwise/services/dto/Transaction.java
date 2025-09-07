package org.split.splitwise.services.dto;

import lombok.Getter;
import lombok.Setter;
import org.split.splitwise.models.User;

@Setter
@Getter
public class Transaction {

    private User fromUser;

    private User toUser;

    private double settleUpAmount;
}
