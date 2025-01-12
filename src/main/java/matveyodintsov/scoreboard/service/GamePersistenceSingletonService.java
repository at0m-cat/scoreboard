package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.repository.GamePersistenceRepository;

import java.util.ArrayList;
import java.util.List;

public class GamePersistenceSingletonService implements Service<Game> {

    private static volatile GamePersistenceSingletonService instance;
    private final GameService service;

    public GamePersistenceSingletonService() {
        GamePersistenceRepository gameRepository = new GamePersistenceRepository();
        this.service = new GameService(gameRepository);
    }

    public static GamePersistenceSingletonService getInstance() {
        if (instance == null) {
            synchronized (GamePersistenceSingletonService.class) {
                if (instance == null) {
                    instance = new GamePersistenceSingletonService();
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
