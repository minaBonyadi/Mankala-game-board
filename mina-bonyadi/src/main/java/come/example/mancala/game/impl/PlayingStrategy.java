package come.example.mancala.game.impl;

import come.example.mancala.game.dto.PlayingResponse;
import come.example.mancala.game.enumeration.StrategyName;

public interface PlayingStrategy {
    PlayingResponse handleTheGame();
    StrategyName getStrategyName();

}
