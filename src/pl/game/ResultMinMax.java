package pl.game;

public class ResultMinMax {

    Field field;
    int score;

    public ResultMinMax(int score, Field filed) {
        this.field = filed;
        this.score = score;
    }

    public Field getField() {
        return field;
    }

    public int getScore() {
        return score;
    }
}
