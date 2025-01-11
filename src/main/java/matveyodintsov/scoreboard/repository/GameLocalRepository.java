package matveyodintsov.scoreboard.repository;

import matveyodintsov.scoreboard.model.Game;

import java.util.*;


public class GameLocalRepository implements Repository<Game> {

    private final Map<UUID, Game> repository = new HashMap<>();

    @Override
    public Game getByKey(String uuid) {
        return repository.get(UUID.fromString(uuid));
    }

    @Override
    public List<Game> getAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public void save(Game game) {
        repository.put(game.getUuid(), game);
    }

    @Override
    public void delete(Game game) {
        repository.remove(game.getUuid());
    }
}
