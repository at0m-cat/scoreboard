package matveyodintsov.scoreboard.service.player;

import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.repository.Repository;
import matveyodintsov.scoreboard.service.MainService;

public class PlayerService extends MainService<Player> {

    public PlayerService(Repository<Player> repository) {
        super(repository);
    }

}
