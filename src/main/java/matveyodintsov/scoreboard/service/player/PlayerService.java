package matveyodintsov.scoreboard.service.player;

import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.repository.Repository;
import matveyodintsov.scoreboard.service.MainService;

public class PlayerService extends MainService<Player> {

    public PlayerService(Repository<Player> repository) {
        super(repository);
    }

    // todo 1-save, 2-get : createOrGetPlayer

    public Player getOrCreatePlayer(String playerName) {
        Player player = repository.getByKey(playerName);
        if (player == null) {
            player = new Player(playerName);
            save(player);
        }
        return player;
    }
}
