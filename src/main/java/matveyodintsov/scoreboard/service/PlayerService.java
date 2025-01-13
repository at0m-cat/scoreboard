package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.repository.Repository;

public class PlayerService extends BaseService<Player> {

    public PlayerService(Repository<Player> repository) {
        super(repository);
    }

    public Player getOrCreatePlayer(String playerName) {
        Player player = repository.getByKey(playerName);
        if (player == null) {
            player = new Player(playerName);
            save(player);
        }
        return player;
    }
}
