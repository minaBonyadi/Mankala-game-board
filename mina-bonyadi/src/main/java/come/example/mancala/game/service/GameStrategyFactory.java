package come.example.mancala.game.service;

import come.example.mancala.game.impl.PlayingStrategy;
import come.example.mancala.game.enumeration.StrategyName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class GameStrategyFactory {
    private Map<StrategyName, PlayingStrategy> strategies;

    @Autowired
    public GameStrategyFactory(Set<PlayingStrategy> strategySet) {
        createStrategy(strategySet);
    }

    public PlayingStrategy findStrategy(StrategyName strategyName) {
        return strategies.get(strategyName);
    }
    private void createStrategy(Set<PlayingStrategy> strategySet) {
        strategies = new HashMap<>();
        strategySet.forEach(
                strategy ->strategies.put(strategy.getStrategyName(), strategy));
    }
}
