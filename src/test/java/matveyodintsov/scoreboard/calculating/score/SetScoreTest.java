package matveyodintsov.scoreboard.calculating.score;

import matveyodintsov.scoreboard.calculating.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SetScoreTest {

    @Test
    @DisplayName("Winning set with 6:5 game points")
    public void testWinningSetWithSixPointsPlayerFirstAndFivePointsPlayerSecond() {
        SetScore setScore = new SetScore();
        setScore.setPlayerScores(0, 6);
        setScore.setPlayerScores(1, 5);
        for (int i = 0; i < 23; i++) {
            setScore.pointWon(0);
        }
        assert (setScore.pointWon(0)).equals(State.PLAYER_ONE_WON);
    }

    @Test
    @DisplayName("Winning set with 5:4 game points")
    public void testWinningSetWithFivePointsPlayerFirstAndFourPointsPlayerSecond() {
        SetScore setScore = new SetScore();
        setScore.setPlayerScores(0, 5);
        setScore.setPlayerScores(1, 4);
        for (int i = 0; i < 23; i++) {
            setScore.pointWon(0);
        }
        assert (setScore.pointWon(0)).equals(State.PLAYER_ONE_WON);
    }

    @Test
    @DisplayName("TieBreak with 6:6 game points")
    public void testTieBreakWithSixPointsPlayerFirstAndSixPointsPlayerSecond() {
        SetScore setScore = new SetScore();
        setScore.setPlayerScores(0, 6);
        setScore.setPlayerScores(1, 6);
        assert (setScore.pointWon(0)).equals(State.ONGOING);
    }

    @Test
    @DisplayName("TieBreak with 7:6 game points")
    public void testTieBreakWithSevenPointsPlayerFirstAndSixPointsPlayerSecond() {
        SetScore setScore = new SetScore();
        setScore.setPlayerScores(0, 6);
        setScore.setPlayerScores(1, 6);

        assert (setScore.pointWon(0)).equals(State.ONGOING);
    }

}
