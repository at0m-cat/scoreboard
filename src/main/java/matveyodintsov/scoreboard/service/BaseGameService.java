package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.repository.Repository;

import java.util.List;

public class BaseGameService implements Service<Game> {

    private final Repository<Game> repository;

    public BaseGameService(Repository<Game> repository) {
        this.repository = repository;
    }

    @Override
    public Game getByKey(String uuid) {
        return repository.getByKey(uuid);
    }

    @Override
    public List<Game> getAll() {
        return repository.getAll();
    }

    @Override
    public void save(Game game) {
        repository.save(game);
    }

    @Override
    public void delete(Game val) {
        repository.delete(val);
    }

}
