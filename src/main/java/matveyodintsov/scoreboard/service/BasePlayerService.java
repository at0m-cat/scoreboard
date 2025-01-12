package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.repository.Repository;

import java.util.List;

public class BasePlayerService implements Service<Player> {

    private final Repository<Player> repository;

    public BasePlayerService(Repository<Player> repository) {
        this.repository = repository;
    }

    @Override
    public Player getByKey(String playerName) {
        return repository.getByKey(playerName);
    }

    @Override
    public List<Player> getAll() {
        return repository.getAll();
    }

    @Override
    public void save(Player player) {
        repository.save(player);
    }

    @Override
    public void delete(Player val) {
        repository.delete(val);
    }

    public boolean isPlayer(String playerName) {
        return repository.getByKey(playerName) != null;
    }

}
