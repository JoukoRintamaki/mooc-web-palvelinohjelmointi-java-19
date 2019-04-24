package scoreservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScoreController {
    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private GameRepository gameRepository;

    @PostMapping("/games/{name}/scores")
    public Score addScore(@PathVariable String name, @RequestBody Score score) {
        score.setGame(gameRepository.findByName(name));
        return scoreRepository.save(score);
    }

    @GetMapping("/games/{name}/scores")
    public List<Score> getScores(@PathVariable String name) {
        return scoreRepository.findByGame(gameRepository.findByName(name));
    }

    @GetMapping("/games/{name}/scores/{id}")
    public Score getScore(@PathVariable String name, @PathVariable Long id) {
        return scoreRepository.findByGameAndId(gameRepository.findByName(name), id);
    }

    @DeleteMapping("/games/{name}/scores/{id}")
    public Score deleteScore(@PathVariable String name, @PathVariable Long id) {
        Game game = gameRepository.findByName(name);
        Score score = scoreRepository.findByGameAndId(game, id);
        scoreRepository.delete(score);
        return score;
    }
}