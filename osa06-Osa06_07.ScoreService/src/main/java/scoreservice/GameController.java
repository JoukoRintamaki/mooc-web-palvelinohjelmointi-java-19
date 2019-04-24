package scoreservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/games")
    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/games/{name}")
    public Game getGame(@PathVariable String name) {
        return gameRepository.findByName(name);
    }

    @DeleteMapping("/games/{name}")
    public Game deleteGame(@PathVariable String name) {
        Game game = gameRepository.findByName(name);
        gameRepository.delete(game);
        return game;
    }

    @PostMapping("/games")
    public Game postGame(@RequestBody Game game) {
        return gameRepository.save(game);
    }
}
