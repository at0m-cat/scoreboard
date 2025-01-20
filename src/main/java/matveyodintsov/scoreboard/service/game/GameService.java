package matveyodintsov.scoreboard.service.game;

import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.repository.base.Repository;
import matveyodintsov.scoreboard.service.base.BaseService;

public class GameService extends BaseService<Game> {
    private volatile ScoreCalculationService scoreService;

    public GameService(Repository<Game> repository) {
        super(repository);
    }

    public ScoreCalculationService getScoreService(Game game) {
        if (scoreService == null) {
            synchronized (this) {
                if (scoreService == null) {
                    scoreService = new ScoreCalculationService(game);
                }
            }
        }
        return scoreService;
    }

}
