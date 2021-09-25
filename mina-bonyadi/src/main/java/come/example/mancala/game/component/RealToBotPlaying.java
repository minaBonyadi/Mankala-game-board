package come.example.mancala.game.component;

import come.example.mancala.game.exception.MancalaException;
import come.example.mancala.game.impl.PlayingStrategy;
import come.example.mancala.game.dto.PlayingResponse;
import come.example.mancala.game.enumeration.PlayerType;
import come.example.mancala.game.enumeration.StrategyName;
import come.example.mancala.game.handler.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RealToBotPlaying implements PlayingStrategy {

    private final CommandHandler commandHandler;
    private final RuleManagementImpl ruleHandler;

    public static final int ZERO = 0;
    public static int realStorage;
    public static int botStorage;

    public static Map<Integer, Integer> realPlayer = new HashMap<>();
    public static Map<Integer, Integer> botPlayer = new HashMap<>();

    static {
        botPlayer.put(1, 6);
        botPlayer.put(2, 6);
        botPlayer.put(3, 6);
        botPlayer.put(4, 6);
        botPlayer.put(5, 6);
        botPlayer.put(6, 6);

        realPlayer.put(1, 6);
        realPlayer.put(2, 6);
        realPlayer.put(3, 6);
        realPlayer.put(4, 6);
        realPlayer.put(5, 6);
        realPlayer.put(6, 6);
    }

    @Override
    public PlayingResponse handleTheGame() {
        playByRealPlayer(commandHandler.getInput());
        playByBotPlayer(RandomUtils.nextInt(1, 6));
        commandHandler.printState(realPlayer, botPlayer, realStorage, botStorage);

        return PlayingResponse.builder()
                .realPlayer(realPlayer)
                .botPlayer(botPlayer)
                .realStorage(realStorage)
                .botStorage(botStorage).build();
    }

    private void playByRealPlayer(int index) {
        int value = realPlayer.get(index);
        if (value == ZERO) playAgain(PlayerType.REAL, value);
        realPlayer.put(index, ZERO);

        divideRealPlayerValues(index, value);
        isTheEndOfTheGame(realPlayer, botPlayer);
    }

    private void divideRealPlayerValues(int index, int value) {
        int previousValueOfCurrentIndex = ZERO;
        while (value > ZERO) {
            if (index > ZERO && index < realPlayer.size()) {
                index++;
                previousValueOfCurrentIndex = getRealPreviousValue(index);
                realPlayer.put(index , realPlayer.get(index) + 1);
                value--;
            }
            if (previousValueOfCurrentIndex == ZERO &&
                    value == ZERO) ruleHandler.getExtra(realPlayer, botPlayer, index, PlayerType.REAL);
            if (index == realPlayer.size() && value > ZERO) {
                realStorage++;
                value--;
                playAgain(PlayerType.REAL, value);
                if (value > ZERO) {
                    ruleHandler.switchToBotPlayer(botPlayer, realPlayer, botStorage, realStorage, value);
                    break;
                }
            }
        }
    }

    private int getRealPreviousValue(int index) {
        if (index <= realPlayer.size()) {
            return realPlayer.get(index);
        }
        throw new MancalaException();
    }

    private void playByBotPlayer(int index) {
        int value = botPlayer.get(index);
        botPlayer.put(index, ZERO);

        divideBotPlayerValues(index, value);
        isTheEndOfTheGame(realPlayer, botPlayer);
    }

    private void divideBotPlayerValues(int index, int value){
        int previousValueOfCurrentIndex = ZERO;
        while (value > ZERO) {
            if (index > ZERO && index < botPlayer.size()) {
                index++;
                previousValueOfCurrentIndex = getBotPreviousValue(index);
                botPlayer.put(index, botPlayer.get(index) + 1);
                value--;
            }
            if (previousValueOfCurrentIndex == ZERO &&
                    value == ZERO) ruleHandler.getExtra(realPlayer, botPlayer, index++, PlayerType.BOT);
            if (index == botPlayer.size() && value > ZERO) {
                botStorage++;
                value--;
                playAgain(PlayerType.BOT, value);
                if (value > ZERO) {
                    ruleHandler.switchToRealPlayer(realPlayer, botPlayer, botStorage, realStorage, value);
                    break;
                }
            }
        }
    }

    private int getBotPreviousValue(int index) {
        if (index < botPlayer.size()) {
            return botPlayer.get(index + 1);
        } else {
            return botPlayer.get(index);
        }
    }

    private void playAgain(PlayerType type, int value) {
        if (value == ZERO && !ruleHandler.isTheEndOfTheGame(realPlayer, botPlayer)) {
            commandHandler.printState(realPlayer, botPlayer, realStorage, botStorage);

            if (type.equals(PlayerType.BOT)) {
                playByBotPlayer(RandomUtils.nextInt(1, 6));
            } else {
                playByRealPlayer(commandHandler.getInput());
            }
        }
    }

    private void isTheEndOfTheGame(Map<Integer, Integer> realPlayer, Map<Integer, Integer> botPlayer) {
        if (ruleHandler.isTheEndOfTheGame(realPlayer, botPlayer)) {
            commandHandler.whoWonTheGame(realStorage, botStorage);
        }
    }

    @Override
    public StrategyName getStrategyName() {
        return StrategyName.REALTOBOT;
    }
}
