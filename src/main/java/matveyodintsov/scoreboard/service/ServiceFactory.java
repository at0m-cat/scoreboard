package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.model.Match;
import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.repository.Repository;
import matveyodintsov.scoreboard.service.match.MatchService;
import matveyodintsov.scoreboard.service.player.PlayerService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceFactory {

    private static final Map<String, Object> services = new ConcurrentHashMap<>();

    private ServiceFactory() {
    }

    public static MatchService getMatchService(Repository<Match> repository) {
        String key = createKey(MatchService.class, repository);
        return (MatchService) services.computeIfAbsent(key, k -> new MatchService(repository));
    }

    public static PlayerService getPlayerService(Repository<Player> repository) {
        String key = createKey(PlayerService.class, repository);
        return (PlayerService) services.computeIfAbsent(key, k -> new PlayerService(repository));
    }

    private static String createKey(Class<?> serviceClass, Repository<?> repository) {
        return serviceClass.getName() + ":" + repository.getClass().getName();
    }
}