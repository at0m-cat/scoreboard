package matveyodintsov.scoreboard.calculating.score;

import matveyodintsov.scoreboard.calculating.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Match points")
public class MatchScoreTest {

    @Test
    @DisplayName("Winning the match with 3 (set) points")
    public void testWinMatchWhenThreeSetPoints() {
        MatchScore matchScore = new MatchScore();

        // match points (2 : 2)
        matchScore.setPlayerScores(0, 2);
        matchScore.setPlayerScores(1, 2);

        // player 1 - Win 23 games
        for (int i = 0; i < 23; i++) {
            matchScore.pointWon(0);
        }

        // player 1 - win +1 game == +1 set -> winMatch
        assert (matchScore.pointWon(0)).equals(State.PLAYER_ONE_WON);
    }

    @Test
    @DisplayName("Ongoing match with sets player 2:2")
    public void testOngoingMatchWhenSetsPlayer2() {
        MatchScore matchScore = new MatchScore();

        // Match points (2:2)
        matchScore.setPlayerScores(0, 2);
        matchScore.setPlayerScores(1, 2);

        assert (matchScore.pointWon(0)).equals(State.ONGOING);
    }
}
