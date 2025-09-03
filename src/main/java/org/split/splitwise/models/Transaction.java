package org.split.splitwise.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Transaction {

    private User fromUser;

    private User toUser;

    private double settleUpAmount;
}
