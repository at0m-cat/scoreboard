package matveyodintsov.scoreboard.service.scoreboard;

import matveyodintsov.scoreboard.model.Scoreboard;
import matveyodintsov.scoreboard.repository.Repository;
import matveyodintsov.scoreboard.service.MainService;

public class ScoreboardService extends MainService<Scoreboard> {

    public ScoreboardService(Repository<Scoreboard> scoreboardRepository) {
        super(scoreboardRepository);
    }
}
