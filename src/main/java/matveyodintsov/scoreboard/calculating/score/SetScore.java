package matveyodintsov.scoreboard.calculating.score;

import lombok.Getter;
import matveyodintsov.scoreboard.calculating.GameScore;
import matveyodintsov.scoreboard.calculating.Score;
import matveyodintsov.scoreboard.calculating.State;

public class SetScore extends Score<Integer> {

    @Getter
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
        int opponentScore = getOpponentPlayerScore(playerNumber);

        if (getPlayerScore(playerNumber) == 6 && opponentScore == 6) {
            this.currentGame = new TieBreakGameScore();
            return State.ONGOING;
        }
        if (getPlayerScore(playerNumber) >= 6 && getPlayerScore(playerNumber) - opponentScore >= 2) {
            return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
        }
        this.currentGame = new RegularGameScore();
        return State.ONGOING;
    }
}
