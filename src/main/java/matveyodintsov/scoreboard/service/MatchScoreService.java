package matveyodintsov.scoreboard.service;

import matveyodintsov.scoreboard.calculating.GameScore;
import matveyodintsov.scoreboard.calculating.RegularGamePlayerPoints;
import matveyodintsov.scoreboard.calculating.State;
import matveyodintsov.scoreboard.calculating.score.MatchScore;
import matveyodintsov.scoreboard.calculating.score.SetScore;
import matveyodintsov.scoreboard.model.Match;
import matveyodintsov.scoreboard.model.Scoreboard;

public class MatchScoreService {

    private final Match match;
    private final Scoreboard scoreboard;
    private final MatchScore matchScore;

    public MatchScoreService(Match match) {
        this.match = match;
        this.scoreboard = match.getScoreboard();
        this.matchScore = scoreboard.getMatchScore();
    }

    public void play(int playerNumber) {
        State matchState = matchScore.pointWon(playerNumber);
        updateScoreboard();
        if (matchState == State.PLAYER_ONE_WON || matchState == State.PLAYER_TWO_WON) {
            finalizeMatch(matchState);
        }
    }

    private void updateScoreboard() {
        SetScore currentSetScore = matchScore.getCurrentScore();
        GameScore<?> currentGameScore =  currentSetScore.getCurrentGame();

        scoreboard.setFirstPlayerSetScore(matchScore.getPlayerScore(0));
        scoreboard.setSecondPlayerSetScore(matchScore.getPlayerScore(1));

        scoreboard.setFirstPlayerGameScore(currentSetScore.getPlayerScore(0));
        scoreboard.setSecondPlayerGameScore(currentSetScore.getPlayerScore(1));

        scoreboard.setFirstPlayerScore(String.valueOf(currentGameScore.getPlayerScore(0)));
        scoreboard.setSecondPlayerScore(String.valueOf(currentGameScore.getPlayerScore(1)));
    }

    private void finalizeMatch(State matchState) {
        match.setWinner(matchState == State.PLAYER_ONE_WON
                ? match.getFirstPlayer()
                : match.getSecondPlayer());
        scoreboard.setFirstPlayerScore(RegularGamePlayerPoints.ZERO.name());
        scoreboard.setSecondPlayerScore(RegularGamePlayerPoints.ZERO.name());
    }
}