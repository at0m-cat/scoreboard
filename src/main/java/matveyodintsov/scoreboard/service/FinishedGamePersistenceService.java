package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.repository.GameRepository;

import java.util.ArrayList;
import java.util.List;

public class FinishedGamePersistenceService implements Service<Game> {

    private static volatile FinishedGamePersistenceService instance;
    private final GameService gameService;

    public FinishedGamePersistenceService() {
        GameRepository gameRepository = new GameRepository();
        gameService = new GameService(gameRepository);
    }

    public static FinishedGamePersistenceService getInstance() {
        if (instance == null) {
            synchronized (FinishedGamePersistenceService.class) {
                if (instance == null) {
                    instance = new FinishedGamePersistenceService();
                }
            }
        }
        return instance;
    }

    @Override
    public Game getByKey(String key) {
        return gameService.getByKey(key);
    }

    @Override
    public List<Game> getAll() {
        return new ArrayList<>(gameService.getAll());
    }

    @Override
    public void save(Game val) {
        gameService.save(val);
    }

    @Override
    public void delete(Game val) {
        gameService.delete(val);
    }
}
