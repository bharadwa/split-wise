package org.split.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.util.Pair;

import java.util.List;

@Setter
@Getter
public class AddExpenseRequestDTO {

    private long createdUserId;

    private long expenseId;

    private long groupId;

    private List<Pair> whoPaid;

    private List<Pair> whoHasToPay;

}
