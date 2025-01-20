package matveyodintsov.scoreboard.repository.game;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.repository.base.Repository;
import matveyodintsov.scoreboard.util.AppConst;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class GameLocalRepository implements Repository<Game> {

    private final Map<UUID, Game> repository;
    private volatile List<Game> cachedGames;

    public GameLocalRepository() {
        this.repository = new ConcurrentHashMap<>();
        this.cachedGames = new ArrayList<>();
    }

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
        if (cachedGames.size() > (AppConst.Constants.PAGE_SIZE)) {
            cachedGames.subList(0, AppConst.Constants.PAGE_SIZE);
        }
        repository.remove(game.getUuid());
    }

    @Override
    public long count() {
        return repository.size();
    }

    @Override
    public long countWithName(String playerName) {
        if (cachedGames.isEmpty()) {
            return 0;
        }
        if (playerName == null || playerName.trim().isEmpty()) {
            return cachedGames.size();
        }
        return cachedGames.stream()
                .filter(game -> game.getFirstPlayer().getName().contains(playerName) ||
                        game.getSecondPlayer().getName().contains(playerName))
                .count();
    }

public List<Game> findAllWithPageAndName(String name, int offset, int pageSize) {
    if (cachedGames.isEmpty()) {
        return Collections.emptyList();
    }

    if (name == null || name.trim().isEmpty()) {
        return cachedGames.stream()
                .skip(offset)
                .limit(pageSize)
                .toList();
    }

    List<Game> games = cachedGames.stream()
            .filter(game -> game.getFirstPlayer().getName().contains(name) ||
                    game.getSecondPlayer().getName().contains(name))
            .toList();

    return games.stream()
            .skip(offset)
            .limit(pageSize)
            .toList();
}
}
