package matveyodintsov.scoreboard.service;


import matveyodintsov.scoreboard.model.Game;

import java.util.Random;

public class ScoreCalculationService {

    private final Random RANDOM;

    protected ScoreCalculationService() {
        this.RANDOM = new Random();
    }

    public Game tennisDraw (Game game) {
        return game;
    }

    public Game tennisGame (Game game) {
        return game;
    }

    public Game tennisSet (Game game) {
        return game;
    }

    public Game tennisTieBreak (Game game) {
        return game;
    }


    private int throwDice() {
        int diceFirst = RANDOM.nextInt(6) + 1;
        int diceSecond = RANDOM.nextInt(6) + 1;
        return diceFirst + diceSecond;
    }

}
