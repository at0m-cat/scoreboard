package matveyodintsov.scoreboard.calculating.score;

import matveyodintsov.scoreboard.calculating.RegularGamePlayerPoints;
import matveyodintsov.scoreboard.calculating.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Score points")
public class RegularGameScoreTest {

    @Test
    @DisplayName("Winning game point with 40:0")
    public void testWinningGamePointWith40Score() {
        RegularGameScore regularGameScore = new RegularGameScore();
        // Score 40:0
        regularGameScore.setPlayerScores(0, RegularGamePlayerPoints.FORTY);
        regularGameScore.setPlayerScores(1, RegularGamePlayerPoints.ZERO);
        //
        assert (regularGameScore.pointWon(0)).equals(State.PLAYER_ONE_WON);
    }

    @Test
    @DisplayName("Winning game point with 40:30")
    public void testWinningGamePointWith4030Score() {
        RegularGameScore regularGameScore = new RegularGameScore();
        regularGameScore.setPlayerScores(0, RegularGamePlayerPoints.FORTY);
        regularGameScore.setPlayerScores(1, RegularGamePlayerPoints.THIRTY);

        assert (regularGameScore.pointWon(0)).equals(State.PLAYER_ONE_WON);
    }

    @Test
    @DisplayName("Ongoing in 40:40 scores")
    public void testOngoingIn40Scores() {
        RegularGameScore regularGameScore = new RegularGameScore();
        regularGameScore.setPlayerScores(0, RegularGamePlayerPoints.FORTY);
        regularGameScore.setPlayerScores(1, RegularGamePlayerPoints.FORTY);

        assert (regularGameScore.pointWon(0)).equals(State.ONGOING);
    }

    @Test
    @DisplayName("Advantage player2 in 40:30")
    public void testAdvantagePlayer2In40Scores() {
        RegularGameScore regularGameScore = new RegularGameScore();
        regularGameScore.setPlayerScores(0, RegularGamePlayerPoints.FORTY);
        regularGameScore.setPlayerScores(1, RegularGamePlayerPoints.THIRTY);

        regularGameScore.pointWon(1);
        assert (regularGameScore.getPlayerScore(1)).equals(RegularGamePlayerPoints.FORTY);
        regularGameScore.pointWon(1);
        assert (regularGameScore.getPlayerScore(1)).equals(RegularGamePlayerPoints.ADVANTAGE);
    }

    @Test
    @DisplayName("Advantage transferred to another player")
    public void testAdvantageTransferredToAnotherPlayer() {
        RegularGameScore regularGameScore = new RegularGameScore();
        regularGameScore.setPlayerScores(0, RegularGamePlayerPoints.FORTY);
        regularGameScore.setPlayerScores(1, RegularGamePlayerPoints.THIRTY);

        regularGameScore.pointWon(1);
        assert (regularGameScore.getPlayerScore(1)).equals(RegularGamePlayerPoints.FORTY);
        regularGameScore.pointWon(1);
        assert (regularGameScore.getPlayerScore(1)).equals(RegularGamePlayerPoints.ADVANTAGE);
        regularGameScore.pointWon(0);
        assert (regularGameScore.getPlayerScore(0)).equals(RegularGamePlayerPoints.FORTY);
        regularGameScore.pointWon(0);
        assert (regularGameScore.getPlayerScore(0)).equals(RegularGamePlayerPoints.ADVANTAGE);
    }
}
