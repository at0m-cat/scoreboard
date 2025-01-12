package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.repository.GameLocalRepository;

import java.util.ArrayList;
import java.util.List;

public class GameLocalSingletonService implements Service<Game> {

    private static volatile GameLocalSingletonService instance;
    private final GameService service;

    private GameLocalSingletonService() {
        GameLocalRepository gameLocalRepository = new GameLocalRepository();
        this.service = new GameService(gameLocalRepository);
    }

    public static GameLocalSingletonService getInstance() {
        if (instance == null) {
            synchronized (GameLocalSingletonService.class) {
                if (instance == null) {
                    instance = new GameLocalSingletonService();
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
