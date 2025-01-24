package matveyodintsov.scoreboard.calculating.score;

import matveyodintsov.scoreboard.calculating.GameScore;
import matveyodintsov.scoreboard.calculating.State;

public class TieBreakGameScore extends GameScore<Integer> {

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    public State pointWon(int playerNumber) {
        int playerScore = getPlayerScore(playerNumber);
        int opponentScore = getOpponentPlayerScore(playerNumber);

        setPlayerScores(playerNumber, playerScore + 1);
        if (getPlayerScore(playerNumber) >= 7 && getPlayerScore(playerNumber) - opponentScore >= 2) {
            return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
        }
        return State.ONGOING;
    }
}