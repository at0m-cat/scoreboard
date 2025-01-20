package matveyodintsov.scoreboard.service.match;

import matveyodintsov.scoreboard.model.Match;
import matveyodintsov.scoreboard.repository.base.Repository;
import matveyodintsov.scoreboard.service.base.BaseService;

public class MatchService extends BaseService<Match> {
    private volatile ScoreCalculationService scoreService;

    public MatchService(Repository<Match> repository) {
        super(repository);
    }

    public ScoreCalculationService getScoreService(Match match) {
        if (scoreService == null) {
            synchronized (this) {
                if (scoreService == null) {
                    scoreService = new ScoreCalculationService(match);
                }
            }
        }
        return scoreService;
    }

}
