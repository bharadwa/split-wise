package org.split.splitwise.strategies;

import org.split.splitwise.dtos.Pair;

import java.util.List;

public interface SplitStrategy {

    public List<Pair<Long,Double>> splitExpense(List<Long> userIds,List<Integer> splitValues);

}
