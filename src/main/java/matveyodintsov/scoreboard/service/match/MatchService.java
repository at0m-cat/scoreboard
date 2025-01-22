package matveyodintsov.scoreboard.service.match;

import matveyodintsov.scoreboard.model.Match;
import matveyodintsov.scoreboard.repository.base.Repository;
import matveyodintsov.scoreboard.service.base.BaseService;

public class MatchService extends BaseService<Match> {

    public MatchService(Repository<Match> repository) {
        super(repository);
    }
}
