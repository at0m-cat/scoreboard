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
        if (playerScore.ordinal() <= RegularGamePlayerPoints.THIRTY.ordinal()) {
            setPlayerScores(playerNumber, playerScore.next());
        } else if (playerScore == RegularGamePlayerPoints.FORTY) {
            RegularGamePlayerPoints opponentPlayerScore = getOpponentPlayerScore(playerNumber);
            if (opponentPlayerScore == RegularGamePlayerPoints.ADVANTAGE) {
                setOpponentPlayerScores(playerNumber, RegularGamePlayerPoints.FORTY);
            } else if (opponentPlayerScore == RegularGamePlayerPoints.FORTY) {
                setOpponentPlayerScores(playerNumber, RegularGamePlayerPoints.ADVANTAGE);
            } else {
                return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
            }
        } else if (playerScore == RegularGamePlayerPoints.ADVANTAGE) {
            return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
        } else {
            throw new IllegalStateException();
        }

        return State.ONGOING;
    }
}
