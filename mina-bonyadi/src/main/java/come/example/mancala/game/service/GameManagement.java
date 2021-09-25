package come.example.mancala.game.service;

import come.example.mancala.game.impl.PlayingStrategy;
import come.example.mancala.game.enumeration.StrategyName;
import come.example.mancala.game.handler.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GameManagement {
    private final GameStrategyFactory strategyFactory;
    private final CommandHandler commandHandler;

    public void play(){
        PlayingStrategy strategy = strategyFactory.findStrategy(StrategyName.REALTOBOT);
        while (!commandHandler.stopTheGame()) {
            strategy.handleTheGame();
        }
    }
}
