package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.repository.PlayerRepository;

import java.util.List;

public class PlayerService {

    private final PlayerRepository repository;

    public PlayerService() {
        this.repository = new PlayerRepository();
    }

    public List<Player> getPlayers() {
        return repository.findAllPlayers();
    }

    public boolean isPlayer(String playerName) {
        return repository.findPlayerByName(playerName) != null;
    }

    public Player getPlayer(String playerName) {
        return repository.findPlayerByName(playerName);
    }

    public void save(Player player) {
        repository.save(player);
    }
}
