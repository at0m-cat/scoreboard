package matveyodintsov.scoreboard.service.calculation;


import lombok.Getter;
import matveyodintsov.scoreboard.model.Match;

public class CalcMatchScoreService {

    @Getter
    private final Match match;

    public enum ScoreEnum {
        LOVE(0),
        FIRST(15),
        SECOND(30),
        THIRD(40),
        ADVANTAGE(1),
        GAME(2);

        @Getter
        private final int value;

        ScoreEnum(int value) {
            this.value = value;
        }

        public static ScoreEnum nextScore(ScoreEnum current) {
            return switch (current) {
                case LOVE -> FIRST;
                case FIRST -> SECOND;
                case SECOND -> THIRD;
                case THIRD, ADVANTAGE -> GAME;
                default -> throw new IllegalStateException();
            };
        }
    }

    public CalcMatchScoreService(Match match) {
        this.match = match;
    }

    public void updateScoreboard(String playerName) {
        if (match.getFirstPlayer().getName().equals(playerName)) {
            match.setScoreFirstPlayer(updateScore(match.getScoreFirstPlayer(), true));
        } else if (match.getSecondPlayer().getName().equals(playerName)) {
            match.setScoreSecondPlayer(updateScore(match.getScoreSecondPlayer(), false));
        }
    }

    private int updateScore(int currentScore, boolean isFirstPlayer) {
        ScoreEnum currentEnum = ScoreEnum.values()[findEnumIndex(currentScore)];
        if (currentEnum == ScoreEnum.THIRD) {
            return handleDeuceAndGame(isFirstPlayer);
        }
        return ScoreEnum.nextScore(currentEnum).getValue();
    }

    private int handleDeuceAndGame(boolean isFirstPlayer) {
        int opponentScore = isFirstPlayer ? match.getScoreSecondPlayer() : match.getScoreFirstPlayer();

        if (opponentScore == ScoreEnum.THIRD.getValue()) {
            if (isFirstPlayer ? match.getScoreFirstPlayer() == ScoreEnum.ADVANTAGE.getValue() : match.getScoreSecondPlayer() == ScoreEnum.ADVANTAGE.getValue()) {
                addGame(isFirstPlayer);
                resetScores();
                return ScoreEnum.LOVE.value;
            } else if (isFirstPlayer ? match.getScoreSecondPlayer() == ScoreEnum.ADVANTAGE.getValue() : match.getScoreFirstPlayer() == ScoreEnum.ADVANTAGE.getValue()) {
                resetAdvantage();
                return ScoreEnum.THIRD.value;
            }
            return ScoreEnum.ADVANTAGE.value;
        } else {
            addGame(isFirstPlayer);
            resetScores();
            return ScoreEnum.LOVE.value;
        }
    }

    private void addGame(boolean isFirstPlayer) {
        if (isFirstPlayer) {
            match.setGamesFirstPlayer(match.getGamesFirstPlayer() + 1);
        } else {
            match.setGamesSecondPlayer(match.getGamesSecondPlayer() + 1);
        }
        checkSet(isFirstPlayer);
    }

    private void checkSet(boolean isFirstPlayer) {
        int firstPlayerGames = match.getGamesFirstPlayer();
        int secondPlayerGames = match.getGamesSecondPlayer();

        if (Math.abs(firstPlayerGames - secondPlayerGames) >= 2 &&
                (firstPlayerGames >= 6 || secondPlayerGames >= 6)) {
            if (isFirstPlayer) {
                match.setSetsFirstPlayer(match.getSetsFirstPlayer() + 1);
            } else {
                match.setSetsSecondPlayer(match.getSetsSecondPlayer() + 1);
            }
            resetGames();
            checkMatchWinner();
        }
    }

    private void checkMatchWinner() {
        if (match.getSetsFirstPlayer() == 2) {
            match.setWinner(match.getFirstPlayer());
        } else if (match.getSetsSecondPlayer() == 2) {
            match.setWinner(match.getSecondPlayer());
        }
    }

    private void resetScores() {
        match.setScoreFirstPlayer(ScoreEnum.LOVE.value);
        match.setScoreSecondPlayer(ScoreEnum.LOVE.value);
    }

    private void resetGames() {
        match.setGamesFirstPlayer(0);
        match.setGamesSecondPlayer(0);
    }

    private void resetAdvantage() {
        match.setScoreFirstPlayer(ScoreEnum.THIRD.value);
        match.setScoreSecondPlayer(ScoreEnum.THIRD.value);
    }

    private int findEnumIndex(int value) {
        for (int i = 0; i < ScoreEnum.values().length; i++) {
            if (ScoreEnum.values()[i].getValue() == value) {
                return i;
            }
        }
        throw new IllegalArgumentException();
    }


    private void resetScore() {
        match.setScoreFirstPlayer(ScoreEnum.LOVE.value);
        match.setScoreSecondPlayer(ScoreEnum.LOVE.value);
    }

    private void resetGame() {
        match.setGamesFirstPlayer(0);
        match.setGamesSecondPlayer(0);
    }

}