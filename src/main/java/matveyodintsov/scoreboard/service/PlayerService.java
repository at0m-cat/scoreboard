package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.repository.Repository;

import java.util.List;

public class PlayerService implements Service<Player> {

    private final Repository<Player> repository;

    public PlayerService(Repository<Player> repository) {
        this.repository = repository;
    }

    @Override
    public List<Player> getAll() {
        return repository.getAll();
    }

    @Override
    public Player getByKey(String playerName) {
        return repository.getByKey(playerName);
    }

    @Override
    public void save(Player player) {
        repository.save(player);
    }

    public boolean isPlayer(String playerName) {
        return repository.getByKey(playerName) != null;
    }

}
