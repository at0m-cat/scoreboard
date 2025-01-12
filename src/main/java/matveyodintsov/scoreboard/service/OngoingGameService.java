package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.repository.GameLocalRepository;

import java.util.ArrayList;
import java.util.List;

public class OngoingGameService implements Service<Game> {

    private static OngoingGameService instance;

    private final GameService gameService;

    private OngoingGameService() {
        GameLocalRepository gameLocalRepository = new GameLocalRepository();
        this.gameService = new GameService(gameLocalRepository);
    }

    public static OngoingGameService getInstance() {
        if (instance == null) {
            instance = new OngoingGameService();
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
