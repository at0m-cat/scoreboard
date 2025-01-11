package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.repository.Repository;

import java.util.List;

public class GameService {

    private final Repository<Game> repository;

    public GameService(Repository<Game> repository) {
        this.repository = repository;
    }

    public void save(Game game) {
        repository.save(game);
    }

    public Game getGameByUuid(String uuid) {
        return repository.getByKey(uuid);
    }

    public List<Game> getGames() {
        return repository.getAll();
    }
}
