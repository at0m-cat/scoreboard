package matveyodintsov.scoreboard.service.match;

import matveyodintsov.scoreboard.model.Match;
import matveyodintsov.scoreboard.repository.Repository;
import matveyodintsov.scoreboard.service.MainService;

public class MatchService extends MainService<Match> {

    public MatchService(Repository<Match> repository) {
        super(repository);
    }
}
