package come.example.mancala.game.controller;

import come.example.mancala.game.impl.PlayingStrategy;
import come.example.mancala.game.service.GameManagement;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mancala")
@AllArgsConstructor
public class GameController {

    private final GameManagement gameManagement;

    @PostMapping("/start")
    public void doPlay(@RequestBody PlayingStrategy strategy) {
        gameManagement.play();
    }

}
