package matveyodintsov.scoreboard.calculating;

public enum RegularGamePlayerPoints {

    ZERO, FIFTEEN, THIRTY, FORTY, ADVANTAGE;

    public RegularGamePlayerPoints next() {
        if (this == ADVANTAGE) {
            throw new IllegalStateException();
        } else {
            return RegularGamePlayerPoints.values()[this.ordinal() + 1];
        }
    }

}
