package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.repository.GamePersistenceRepository;

import java.util.ArrayList;
import java.util.List;

public class FinishedGamePersistenceService implements Service<Game> {

    private static volatile FinishedGamePersistenceService instance;
    private final GameService service;

    public FinishedGamePersistenceService() {
        GamePersistenceRepository gameRepository = new GamePersistenceRepository();
        this.service = new GameService(gameRepository);
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
        return service.getByKey(key);
    }

    @Override
    public List<Game> getAll() {
        return new ArrayList<>(service.getAll());
    }

    @Override
    public void save(Game val) {
        service.save(val);
    }

    @Override
    public void delete(Game val) {
        service.delete(val);
    }
}
