package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.calculating.score.MatchScore;
import matveyodintsov.scoreboard.calculating.score.RegularGameScore;
import matveyodintsov.scoreboard.calculating.score.SetScore;
import matveyodintsov.scoreboard.model.Match;
import matveyodintsov.scoreboard.model.Scoreboard;

public class MatchScoreService {

    private final Match match;
    private final Scoreboard scoreboard;
    private final MatchScore matchScore;
    private final SetScore setScore;
    private final RegularGameScore regularGameScore;

    public MatchScoreService(Match match) {
        this.match = match;
        this.scoreboard = match.getScoreboard();
        this.matchScore = scoreboard.getMatchScore();
        this.setScore = scoreboard.getSetScore();
        this.regularGameScore = scoreboard.getRegularGameScore();
    }

    public void update(int playerNumber) {

    }
}