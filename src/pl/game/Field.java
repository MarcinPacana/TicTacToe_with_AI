package pl.game;

public class Field {

    public static String[] znak = new String[]{" ", "O", "X"};
    private int status;
    private int row;
    private int col;

    public Field() {
        this.status = 0;
    }

    public Field(int status) {
        this.setStatus(status);
    }

    public Field(int status, int row, int col) {
        this.setStatus(status);
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        if (status != 1 && status != 2) {
            this.status = 0;
        } else {
            this.status = status;
        }
    }

    public String toString() {
        return znak[this.getStatus()];
    }
}
