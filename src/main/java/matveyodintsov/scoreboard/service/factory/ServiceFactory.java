package matveyodintsov.scoreboard.service.factory;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.repository.base.Repository;
import matveyodintsov.scoreboard.service.game.GameService;
import matveyodintsov.scoreboard.service.player.PlayerService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceFactory {

    private static final Map<String, Object> services = new ConcurrentHashMap<>();

    private ServiceFactory() {
    }

    public static GameService getGameService(Repository<Game> repository) {
        String key = createKey(GameService.class, repository);
        return (GameService) services.computeIfAbsent(key, k -> new GameService(repository));
    }

    public static PlayerService getPlayerService(Repository<Player> repository) {
        String key = createKey(PlayerService.class, repository);
        return (PlayerService) services.computeIfAbsent(key, k -> new PlayerService(repository));
    }

    private static String createKey(Class<?> serviceClass, Repository<?> repository) {
        return serviceClass.getName() + ":" + repository.getClass().getName();
    }
}