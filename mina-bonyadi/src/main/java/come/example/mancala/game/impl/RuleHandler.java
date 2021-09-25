package come.example.mancala.game.impl;

import come.example.mancala.game.enumeration.PlayerType;

import java.util.Map;

public interface RuleHandler {

    void switchToBotPlayer(Map<Integer, Integer> botPlayer ,Map<Integer, Integer> realPlayer,
                           int botStorage ,int realStorage ,int value);

    void switchToRealPlayer(Map<Integer, Integer> realPlayer ,Map<Integer, Integer> botPlayer,
                            int realStorage ,int botStorage ,int value);

    void getExtra(Map<Integer, Integer> realPlayer , Map<Integer, Integer> botPlayer, int index , PlayerType type);

    boolean isTheEndOfTheGame(Map<Integer, Integer> realPlayer ,Map<Integer, Integer> botPlayer);
}
