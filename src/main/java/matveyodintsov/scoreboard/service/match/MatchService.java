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
        Match match = new Match();
        match.setFirstPlayer(firstPlayer);
        match.setSecondPlayer(secondPlayer);
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.setUuid(match.getUuid());
        match.setScoreboard(scoreboard);
        super.save(match);
        return match;
    }

}
