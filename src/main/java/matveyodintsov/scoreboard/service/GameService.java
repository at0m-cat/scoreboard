package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.repository.Repository;

public class GameService extends BaseService<Game> {

    public GameService(Repository<Game> repository) {
        super(repository);
    }
}
