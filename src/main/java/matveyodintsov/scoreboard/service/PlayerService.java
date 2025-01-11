package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.repository.PlayerRepository;
import matveyodintsov.scoreboard.repository.Repository;

import java.util.List;

public class PlayerService {



    private final Repository<Player> repository;

    public PlayerService(Repository<Player> repository) {
        this.repository = repository;
    }

    public List<Player> getPlayers() {
        return repository.getAll();
    }

    public boolean isPlayer(String playerName) {
        return repository.getByKey(playerName) != null;
    }

    public Player getPlayer(String playerName) {
        return repository.getByKey(playerName);
    }

    public void save(Player player) {
        repository.save(player);
    }
}
