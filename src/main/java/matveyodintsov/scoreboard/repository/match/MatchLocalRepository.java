package matveyodintsov.scoreboard.repository.match;

import matveyodintsov.scoreboard.model.Match;
import matveyodintsov.scoreboard.repository.base.Repository;
import matveyodintsov.scoreboard.util.AppConst;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class MatchLocalRepository implements Repository<Match> {

    private final Map<UUID, Match> repository;
    private volatile List<Match> cachedMatches;

    public MatchLocalRepository() {
        this.repository = new ConcurrentHashMap<>();
        this.cachedMatches = new ArrayList<>();
    }

    @Override
    public Match getByKey(String uuid) {
        return repository.get(UUID.fromString(uuid));
    }

    @Override
    public List<Match> getAll() {
        return cachedMatches;
    }

    @Override
    public void save(Match match) {
        repository.put(match.getUuid(), match);
        cachedMatches = new ArrayList<>(repository.values());
    }

    @Override
    public void delete(Match match) {
        if (cachedMatches.size() > (AppConst.Constants.PAGE_SIZE)) {
            cachedMatches.subList(0, AppConst.Constants.PAGE_SIZE);
        }
        repository.remove(match.getUuid());
    }

    @Override
    public long count() {
        return repository.size();
    }

    @Override
    public long countWithName(String playerName) {
        if (cachedMatches.isEmpty()) {
            return 0;
        }
        if (playerName == null || playerName.trim().isEmpty()) {
            return cachedMatches.size();
        }
        return cachedMatches.stream()
                .filter(match -> match.getFirstPlayer().getName().contains(playerName) ||
                        match.getSecondPlayer().getName().contains(playerName))
                .count();
    }

public List<Match> findAllWithPageAndName(String name, int offset, int pageSize) {
    if (cachedMatches.isEmpty()) {
        return Collections.emptyList();
    }

    if (name == null || name.trim().isEmpty()) {
        return cachedMatches.stream()
                .skip(offset)
                .limit(pageSize)
                .toList();
    }

    List<Match> matches = cachedMatches.stream()
            .filter(game -> game.getFirstPlayer().getName().contains(name) ||
                    game.getSecondPlayer().getName().contains(name))
            .toList();

    return matches.stream()
            .skip(offset)
            .limit(pageSize)
            .toList();
}
}
