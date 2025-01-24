package matveyodintsov.scoreboard.service.match;

import matveyodintsov.scoreboard.model.Match;
import matveyodintsov.scoreboard.model.Player;
import matveyodintsov.scoreboard.model.Scoreboard;
import matveyodintsov.scoreboard.repository.Repository;
import matveyodintsov.scoreboard.service.MainService;

public class MatchService extends MainService<Match> {

    public MatchService(Repository<Match> repository) {
        super(repository);
    }

    public Match createAndSaveMatchRegistration(Player firstPlayer, Player secondPlayer) {
        Match match = new Match(firstPlayer, secondPlayer);
        Scoreboard scoreboard = new Scoreboard(match.getUuid());
        match.setScoreboard(scoreboard);
        repository.save(match);
        return match;
    }

}
