package come.example.mancala.game.handler;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class CommandHandler {
    private boolean stopTheGame = false;

    @PostConstruct
    public void printRules() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n Come on! enter your name and start the game :)  ");
        String name = sc.nextLine();

        System.out.println("\t\t*************************************");
        System.out.println("\t\t  "+name.toUpperCase(Locale.ROOT)+" WELCOME TO MANCALA PLAY BOARD!    ");
        System.out.println("\t\t*************************************");
        System.out.println("Game Rules.....");
        System.out.println("\t1 : Minimum Pits Which you can select is 1 and Max is 6. ");
        System.out.println("\t2 : Every Player has to play this game anticlockwise.");
        System.out.println("\t\t2.1 : For Player Bot Player : Right to Left ( Pits can be seen as on board 6->5->4->3->2->1 ). ");
        System.out.println("\t\t2.2 : For Player Real Player : Left to Right ( Pits can be seen as on board 1->2->3->4->5->6 ). ");

        System.out.println("\t3 : In a display console, Pits which called ( Mancala ) sown as 'O', Doesn't consider as your Pits.");
        System.out.println("\t4 : Don't provide Character or any special symbol, While providing Input from console.");
        System.out.println("\t5 : To Get More user experience Time delay has been introduce ( SECONDS=1 ) between each move.");
        System.out.println("\t6 : Always Think Three moves Ahead....!!!");
        System.out.println("");
        System.out.println("Mancala dashboard will appear very soon... Enjoy the game... ");
        System.out.println("___________________________________________________________________");
    }

    public int getInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\t\t*************************************");
        System.out.println("\t\tEnter a pit number between 1 to 6 : ");
        System.out.println("\t\tPay attention your selected hole should be filled!!");
        System.out.println("\t\t*************************************");
        int input = Integer.parseInt(sc.next());
        if (input > 6 || input < 1) {
            System.out.println("\n\t\t********You enter wrong number***************");
            input = getInput();
        }
        return input;
    }

    public void printState(Map<Integer, Integer> realPlayer , Map<Integer, Integer> botPlayer
            , int realStorage , int botStorage){
        System.out.println("\n___________________________________________________________________\n");
        reverseMap(botPlayer).forEach((k, v) -> System.out.print("    " + v));
        System.out.println("\n"+botStorage+"\t\t\t\t\t\t\t\t "+realStorage);
        realPlayer.forEach((k, v) -> System.out.print("    "+ v));
    }

    public void whoWonTheGame(int realStorage , int botStorage) {
        if (realStorage > botStorage) {
            System.out.println("Real Player Won The Mancala :))))");
        } else if (botStorage > realStorage) {
            System.out.println("Bot Player Won The Mancala :(((");
        } else {
            System.out.println("Real player is equal to Bot player :)");
        }
        stopTheGame = true;
    }

    public boolean stopTheGame(){
        return stopTheGame;
    }

    private Map<Integer, Integer> reverseMap(Map<Integer, Integer> map) {
        List<Map.Entry<Integer, Integer>> mapEntry = new LinkedList<>(map.entrySet());
        Collections.reverse(mapEntry);
        Map<Integer, Integer> reversedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : mapEntry) {
            reversedMap.put(entry.getKey(), entry.getValue());
        }
        return (reversedMap);
    }
}
