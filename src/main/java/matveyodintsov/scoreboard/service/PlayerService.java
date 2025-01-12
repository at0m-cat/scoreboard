package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.repository.Repository;

public class PlayerService extends BaseService<Player> {

    public PlayerService(Repository<Player> repository) {
        super(repository);
    }

    public boolean isPlayer(String playerName) {
        return repository.getByKey(playerName) != null;
    }
}
