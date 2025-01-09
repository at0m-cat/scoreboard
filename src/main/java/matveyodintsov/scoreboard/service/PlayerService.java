package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.repository.PlayerRepository;

public class PlayerService {

    private final PlayerRepository repository;

    public PlayerService() {
        this.repository = new PlayerRepository();
    }

    public boolean isPlayer(String playerName) {
        return repository.findPlayerByName(playerName) != null;
    }
}
