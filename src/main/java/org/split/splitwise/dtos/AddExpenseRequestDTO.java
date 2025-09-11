package org.split.splitwise.dtos;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;
import org.split.splitwise.dtos.SplitType;
import org.split.splitwise.dtos.Pair;

import java.util.List;

@Setter
@Getter
public class AddExpenseRequestDTO {

    private long createdUserId;

    private SplitType splitType;

    private List<Long> users;

    //who paid the amount
    private List<Pair<Long,Integer>> paidUsers;

    private List<Integer> splitValues;

    private long groupId;

    private String description;


}
