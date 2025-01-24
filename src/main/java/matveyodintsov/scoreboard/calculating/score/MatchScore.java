package matveyodintsov.scoreboard.calculating.score;

import matveyodintsov.scoreboard.calculating.Score;
import matveyodintsov.scoreboard.calculating.State;

public class MatchScore extends Score<Integer> {

    private SetScore currentScore;

    public MatchScore() {
        this.currentScore = new SetScore();
    }

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public State pointWon(int playerNumber) {
        State state = currentScore.pointWon(playerNumber);
        if (state == State.PLAYER_ONE_WON) {
            return matchWon(0);
        } else if (state == State.PLAYER_TWO_WON) {
            return matchWon(1);
        }
        return State.ONGOING;
    }

    private State matchWon(int playerNumber) {
        setPlayerScores(playerNumber, getPlayerScore(playerNumber) + 1);
        this.currentScore = new SetScore();
        if (getPlayerScore(playerNumber) == 3) {
            if (playerNumber == 0) {
                return State.PLAYER_ONE_WON;
            } else {
                return State.PLAYER_TWO_WON;
            }
        }
        return State.ONGOING;
    }
}
