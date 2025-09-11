package org.split.splitwise.strategies;

import org.split.splitwise.dtos.SplitType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class SplitStrategyFactory {

    private final Map<SplitType, SplitStrategy> splitStrategyMap;

    @Autowired
    public SplitStrategyFactory(EqualSplitStrategy equalSplitStrategy, ExactSplitStrategy exactSplitStrategy,
                                PercentSplitStrategy percentSplitStrategy, RatioSplitStrategy ratioSplitStrategy) {
        splitStrategyMap = new EnumMap<SplitType, SplitStrategy>(SplitType.class);
        splitStrategyMap.put(SplitType.EQUAL, equalSplitStrategy);
        splitStrategyMap.put(SplitType.EXACT, exactSplitStrategy);
        splitStrategyMap.put(SplitType.PERCENT, percentSplitStrategy);
        splitStrategyMap.put(SplitType.RATIO, ratioSplitStrategy);
    }
}
