package come.example.mancala.game.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class PlayingResponse {

    int realStorage;

    int botStorage;

    Map<Integer,Integer> realPlayer;

    Map<Integer,Integer> botPlayer;

}
