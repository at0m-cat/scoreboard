package matveyodintsov.scoreboard.calculating.score;

import matveyodintsov.scoreboard.calculating.GameScore;
import matveyodintsov.scoreboard.calculating.Score;
import matveyodintsov.scoreboard.calculating.State;

public class SetScore extends Score<Integer> {

    private GameScore<?> currentGame;

    public SetScore() {
        this.currentGame = new RegularGameScore();
    }

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public State pointWon(int playerNumber) {
        State gameState = currentGame.pointWon(playerNumber);
        if (gameState == State.PLAYER_ONE_WON) {
            return gameWon(0);
        } else if (gameState == State.PLAYER_TWO_WON) {
            return gameWon(1);
        }
        return State.ONGOING;
    }

    private State gameWon(int playerNumber) {
        setPlayerScores(playerNumber, getPlayerScore(playerNumber) + 1);
        this.currentGame = new RegularGameScore();

        if (getPlayerScore(playerNumber) == 6) {

            // TODO 2 games advantage and tiebreak logic

            if (playerNumber == 0) {
                return State.PLAYER_ONE_WON;
            } else {
                return State.PLAYER_TWO_WON;
            }

        } else {
            return State.ONGOING;
        }
    }
}
