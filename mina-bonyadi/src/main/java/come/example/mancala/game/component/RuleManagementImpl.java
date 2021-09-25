package come.example.mancala.game.component;

import come.example.mancala.game.exception.MancalaException;
import come.example.mancala.game.impl.RuleHandler;
import come.example.mancala.game.enumeration.PlayerType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RuleManagementImpl implements RuleHandler {

    @Override
    public void switchToBotPlayer(Map<Integer, Integer> botPlayer ,Map<Integer, Integer> realPlayer,
                                  int botStorage ,int realStorage ,int value){
        int firstIndex = 1;
        while(value > RealToBotPlaying.ZERO){
            RealToBotPlaying.botPlayer.put(firstIndex,(botPlayer.get(firstIndex)) + 1);
            firstIndex++; value--;
            if (firstIndex > botPlayer.size() && value > RealToBotPlaying.ZERO){
                RealToBotPlaying.botStorage++; value--;
                switchToRealPlayer(realPlayer, botPlayer, realStorage, botStorage, value);
                break;
            }
        }
    }

    @Override
    public void switchToRealPlayer(Map<Integer, Integer> realPlayer ,Map<Integer, Integer> botPlayer,
                                   int realStorage ,int botStorage ,int value){
        int index = 1;
        while(value > RealToBotPlaying.ZERO){
            RealToBotPlaying.realPlayer.put(index,(realPlayer.get(index)) + 1);
            index++; value--;
            if (index > realPlayer.size() && value > RealToBotPlaying.ZERO){
                RealToBotPlaying.realStorage++; value--;
                switchToBotPlayer(botPlayer, realPlayer, botStorage, realStorage, value);
                break;
            }
        }
    }

    @Override
    public void getExtra(Map<Integer, Integer> realPlayer ,Map<Integer, Integer> botPlayer, int index ,PlayerType type){

        if (type.equals(PlayerType.BOT) && index > RealToBotPlaying.ZERO && realPlayer.get(getFrontHoleIndex(index)) != 0){
            RealToBotPlaying.botStorage += (realPlayer.get(getFrontHoleIndex(index))) + 1;
            botPlayer.put(index, RealToBotPlaying.ZERO);
            realPlayer.put(getFrontHoleIndex(index), RealToBotPlaying.ZERO);

        } else if (type.equals(PlayerType.REAL) && index > RealToBotPlaying.ZERO && botPlayer.get(getFrontHoleIndex(index)) != 0){
            RealToBotPlaying.realStorage += (botPlayer.get(getFrontHoleIndex(index))) + 1;
            realPlayer.put(index, RealToBotPlaying.ZERO);
            botPlayer.put(getFrontHoleIndex(index), RealToBotPlaying.ZERO);
        }
    }

    @Override
    public boolean isTheEndOfTheGame(Map<Integer, Integer> realPlayer ,Map<Integer, Integer> botPlayer) {

        if (realPlayer.values().stream().allMatch(value -> value == RealToBotPlaying.ZERO) ||
                botPlayer.values().stream().allMatch(value -> value == RealToBotPlaying.ZERO)) {

            RealToBotPlaying.realStorage += RealToBotPlaying.realPlayer.values().stream().mapToInt(Integer::intValue).sum();
            RealToBotPlaying.realPlayer.replaceAll((k, v) -> v = RealToBotPlaying.ZERO);
            RealToBotPlaying.botStorage += botPlayer.values().stream().mapToInt(Integer::intValue).sum();
            RealToBotPlaying.botPlayer.replaceAll((k, v) -> v = RealToBotPlaying.ZERO);
            return true;
        }
        return false;
    }

    private int getFrontHoleIndex(int index) {
        switch (index){
            case 1: index = 6; break;
            case 2: index = 5; break;
            case 3: index = 4; break;
            case 4: index = 3; break;
            case 5: index = 2; break;
            case 6: index = 1; break;
            default:
                throw new MancalaException();
        }
        return index;
    }
}
