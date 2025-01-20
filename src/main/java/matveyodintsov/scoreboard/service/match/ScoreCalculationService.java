package matveyodintsov.scoreboard.service.match;


import matveyodintsov.scoreboard.model.Match;
import matveyodintsov.scoreboard.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class ScoreCalculationService {

    private final Random random;
    private final Map<Player, Points> pointsMap;
    private final Match currentMatch;

    enum SymmetryDice {
        EVEN,
        UNEVEN
    }

    enum Points {
        LOVE,
        FIRST,
        SECOND,
        THIRD,
        ADVANTAGE,
        GAME
    }

    protected ScoreCalculationService(Match match) {
        this.currentMatch = match;
        this.random = new Random();
        this.pointsMap = new HashMap<>();
    }

    public void play() {
    }

    private SymmetryDice throwDice() {
        int diceFirst = random.nextInt(6) + 1;
        int diceSecond = random.nextInt(6) + 1;
        int sum = diceFirst + diceSecond;
        return sum % 2 == 0 ? SymmetryDice.EVEN : SymmetryDice.UNEVEN;
    }

    private void CalcScore() {
    }


    private void tennisDraw() {
    }

    private void tennisGame() {
    }

    private void tennisSet() {
    }

    private void tennisTieBreak() {
    }

}
