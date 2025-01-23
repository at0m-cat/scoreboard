package matveyodintsov.scoreboard.service.points;

import java.util.ArrayList;
import java.util.List;

public abstract class Score<T> {

    private final List<T> playerScores = new ArrayList<>();

    protected abstract T getZeroScore();

    public Score() {
        playerScores.add(getZeroScore());
        playerScores.add(getZeroScore());
    }

    public T getPlayerScore(int playerNumber) {
        return playerScores.get(playerNumber);
    }

    public T getOpponentPlayerScore(int playerNumber) {
        return playerScores.get(playerNumber == 0 ? 1 : 0);
    }

    public void setPlayerScores(int playerNumber, T playerScore) {
        playerScores.set(playerNumber, playerScore);
    }

    public void setOpponentPlayerScores(int playerNumber, T playerScore) {
        playerScores.set(playerNumber == 0 ? 1 : 0, playerScore);
    }

    public abstract State pointWon(int playerNumber);
}
