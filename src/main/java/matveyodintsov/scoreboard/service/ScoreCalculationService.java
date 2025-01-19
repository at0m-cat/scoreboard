package matveyodintsov.scoreboard.service;


import matveyodintsov.scoreboard.model.Game;
import matveyodintsov.scoreboard.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class ScoreCalculationService {

    private final Random RANDOM;
    private final Map<Player, Points> pointsMap;

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
        DEUCE,
        GAME
    }

    protected ScoreCalculationService() {
        this.RANDOM = new Random();
        this.pointsMap = new HashMap<>();
    }

    public void play(Game game) {
    }

    private SymmetryDice throwDice() {
        int diceFirst = RANDOM.nextInt(6) + 1;
        int diceSecond = RANDOM.nextInt(6) + 1;
        int sum = diceFirst + diceSecond;
        return sum % 2 == 0 ? SymmetryDice.EVEN : SymmetryDice.UNEVEN;
    }

    private void CalcScore(Map<Player, Points> playerPointsMap) {
    }


    private void tennisDraw(Game game) {
    }

    private Game tennisGame(Game game) {
        return game;
    }

    private Game tennisSet(Game game) {
        return game;
    }

    private Game tennisTieBreak(Game game) {
        return game;
    }

}
