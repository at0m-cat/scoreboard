package matveyodintsov.scoreboard.repository;

import matveyodintsov.scoreboard.model.Game;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class GameLocalRepository implements Repository<Game> {

    private final Map<UUID, Game> repository = new ConcurrentHashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private volatile List<Game> cachedGames;

    @Override
    public Game getByKey(String uuid) {
        return repository.get(UUID.fromString(uuid));
    }

    @Override
    public List<Game> getAll() {
        lock.readLock().lock();
        try {
            return new ArrayList<>(cachedGames);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void save(Game game) {
        lock.writeLock().lock();
        try {
            repository.put(game.getUuid(), game);
            cachedGames = new ArrayList<>(repository.values());
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(Game game) {
        repository.remove(game.getUuid());
    }

    @Override
    public long count() {
        return repository.size();
    }

    @Override
    public long countWithName(String playerName) {
        lock.readLock().lock();
        try {
            if (playerName == null || playerName.trim().isEmpty()) {
                return cachedGames.size();
            }
            return cachedGames.stream()
                    .filter(game -> game.getFirstPlayer().getName().contains(playerName) ||
                            game.getSecondPlayer().getName().contains(playerName))
                    .count();
        } finally {
            lock.readLock().unlock();
        }
    }

    public List<Game> findAllWithPageAndName(String name, int offset, int pageSize) {
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
