package matveyodintsov.scoreboard.repository.match;

import matveyodintsov.scoreboard.model.Match;
import matveyodintsov.scoreboard.repository.LocalRepository;
import matveyodintsov.scoreboard.util.AppConst;

import java.util.*;


public class MatchLocalRepository extends LocalRepository<Match, UUID> {

    @Override
    public void save(Match match) {
        repository.put(match.getUuid(), match);
        cachedList = new ArrayList<>(repository.values());
    }

    @Override
    public void delete(Match match) {
        if (cachedList.size() > (AppConst.Constants.PAGE_SIZE)) {
            cachedList.subList(0, AppConst.Constants.PAGE_SIZE);
        }
        repository.remove(match.getUuid());
    }

    @Override
    public long countWithName(String playerName) {
        if (cachedList.isEmpty()) {
            return 0;
        }
        if (playerName == null || playerName.trim().isEmpty()) {
            return cachedList.size();
        }
        return cachedList.stream()
                .filter(match -> match.getFirstPlayer().getName().contains(playerName) ||
                        match.getSecondPlayer().getName().contains(playerName))
                .count();
    }

    @Override
    public List<Match> findAllWithPageAndName(String name, int offset, int pageSize) {
        if (cachedList.isEmpty()) {
            return Collections.emptyList();
        }

        if (name == null || name.trim().isEmpty()) {
            return cachedList.stream()
                    .skip(offset)
                    .limit(pageSize)
                    .toList();
        }

        List<Match> matches = cachedList.stream()
                .filter(game -> game.getFirstPlayer().getName().contains(name) ||
                        game.getSecondPlayer().getName().contains(name))
                .toList();

        return matches.stream()
                .skip(offset)
                .limit(pageSize)
                .toList();
    }
}
