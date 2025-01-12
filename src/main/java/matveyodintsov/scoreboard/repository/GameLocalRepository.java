package matveyodintsov.scoreboard.repository;

import matveyodintsov.scoreboard.model.Game;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class GameLocalRepository implements Repository<Game> {

    private final Map<UUID, Game> repository = new ConcurrentHashMap<>();
    private volatile List<Game> cachedGames;

    @Override
    public Game getByKey(String uuid) {
        return repository.get(UUID.fromString(uuid));
    }

    @Override
    public List<Game> getAll() {
        return cachedGames;
    }

    @Override
    public void save(Game game) {
        repository.put(game.getUuid(), game);
        cachedGames = new ArrayList<>(repository.values());
    }

    @Override
    public void delete(Game game) {
        repository.remove(game.getUuid());
    }
}
