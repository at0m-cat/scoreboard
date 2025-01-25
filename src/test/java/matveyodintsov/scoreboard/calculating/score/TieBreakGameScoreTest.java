package matveyodintsov.scoreboard.calculating.score;

import matveyodintsov.scoreboard.calculating.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("TieBreak condition")
public class TieBreakGameScoreTest {

    @Test
    @DisplayName("Winning game with Score points 7:5")
    public void testWinningGameWithScorePointsSevenAndFiveWithFirstAndSecondPlayers() {
        TieBreakGameScore tieBreakGameScore = new TieBreakGameScore();
        tieBreakGameScore.setPlayerScores(0, 6);
        tieBreakGameScore.setPlayerScores(1, 5);

        assert (tieBreakGameScore.pointWon(0)).equals(State.PLAYER_ONE_WON);
    }

    @Test
    @DisplayName("Next tiebreak if difference score more 2")
    public void testNextTiebreakIfDifferenceScoreMore2() {
        TieBreakGameScore tieBreakGameScore = new TieBreakGameScore();
        tieBreakGameScore.setPlayerScores(0, 7);
        tieBreakGameScore.setPlayerScores(1, 6);

        // 7:7
        assert (tieBreakGameScore.pointWon(1)).equals(State.ONGOING);

        // 7:8
        assert (tieBreakGameScore.pointWon(1)).equals(State.ONGOING);

        // 8:8
        assert (tieBreakGameScore.pointWon(0)).equals(State.ONGOING);

        // 9:8
        assert (tieBreakGameScore.pointWon(0)).equals(State.ONGOING);

    }
}
