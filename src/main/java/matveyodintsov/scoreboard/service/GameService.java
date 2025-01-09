package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.repository.GameRepository;

import java.util.List;

public class GameService {

    private final GameRepository repository;

    public GameService() {
        this.repository = new GameRepository();
    }

    public void save(Game game) {
        repository.save(game);
    }

    public List<Game> getGames() {
        return repository.findAllGames();
    }
}
