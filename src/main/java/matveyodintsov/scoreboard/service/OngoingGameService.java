package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.repository.GameLocalRepository;

import java.util.ArrayList;
import java.util.List;

public class OngoingGameService implements Service<Game> {

    private static volatile OngoingGameService instance;
    private final GameService service;

    private OngoingGameService() {
        GameLocalRepository gameLocalRepository = new GameLocalRepository();
        this.service = new GameService(gameLocalRepository);
    }

    public static OngoingGameService getInstance() {
        if (instance == null) {
            synchronized (OngoingGameService.class) {
                if (instance == null) {
                    instance = new OngoingGameService();
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
