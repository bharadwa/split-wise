package org.split.splitwise.strategies;

import org.split.splitwise.dtos.Pair;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RatioSplitStrategy implements  SplitStrategy{
    @Override
    public List<Pair<Long, Double>> splitExpense(List<Long> userIds, List<Integer> splitValues) {
        return List.of();
    }
}
