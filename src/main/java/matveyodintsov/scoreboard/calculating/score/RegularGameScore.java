package matveyodintsov.scoreboard.calculating.score;

import matveyodintsov.scoreboard.calculating.GameScore;
import matveyodintsov.scoreboard.calculating.RegularGamePlayerPoints;
import matveyodintsov.scoreboard.calculating.State;

public class RegularGameScore extends GameScore<RegularGamePlayerPoints> {

    @Override
    protected RegularGamePlayerPoints getZeroScore() {
        return RegularGamePlayerPoints.ZERO;
    }

    @Override
    public State pointWon(int playerNumber) {
        RegularGamePlayerPoints playerScore = getPlayerScore(playerNumber);
        RegularGamePlayerPoints opponentScore = getOpponentPlayerScore(playerNumber);

        if (playerScore.ordinal() <= RegularGamePlayerPoints.THIRTY.ordinal()) {
            setPlayerScores(playerNumber, playerScore.next());
            return State.ONGOING;
        }

        if (playerScore == RegularGamePlayerPoints.FORTY) {
            if (opponentScore == RegularGamePlayerPoints.ADVANTAGE) {
                setOpponentPlayerScores(playerNumber, RegularGamePlayerPoints.FORTY);
            } else if (opponentScore == RegularGamePlayerPoints.FORTY) {
                setPlayerScores(playerNumber, RegularGamePlayerPoints.ADVANTAGE);
            } else {
                return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
            }
            return State.ONGOING;
        }

        if (playerScore == RegularGamePlayerPoints.ADVANTAGE) {
            return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
        }
        throw new IllegalStateException();
    }
}
