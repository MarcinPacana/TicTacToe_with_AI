package pl.game;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private Field[][] tabField;
    private int dimension;
    private int movementCounter = 0;

    public Board(int dimension) {
        this.tabField = new Field[dimension][dimension];
        this.dimension = dimension;
        fillTab();
        prepareBoard();
    }

    private void fillTab() {
        for (int i = 0; i<dimension; i++){
            for (int j = 0; j<dimension; j++){
                this.tabField[i][j] = new Field(0,i,j);
            }
        }
    }

    public Board clone(){
        Board clonedBoard = new Board(this.dimension);
        clonedBoard.setTabField(this.tabField);
        clonedBoard.setMovementCounter(this.movementCounter);
        return clonedBoard;
    }

    public Field[][] getTabField() {
        return tabField;
    }

    public void setTabField(Field[][] tabField) {
        this.tabField = tabField;
    }

    public int getDimension() {
        return dimension;
    }

    public void setSymbol(int row, int col, int symbol){
        tabField[row][col].setStatus(symbol);
    }

    public boolean checkCorrectMovement(int[] move){
        if (this.tabField[move[0]][move[1]].getStatus() == 0){
            return true;
        }else return false;
    }

    public boolean checkValueMovement(int[] move){
        if (move[0] >= dimension || move[1] >= dimension){
            return false;
        }else return true;
    }

    public void drawBoard() {
        System.out.print("\t");
        for (int i = 0; i < dimension; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i<dimension; i++){
            System.out.print(i + "\t");
            for (int j = 0; j<dimension; j++){
                System.out.print(this.tabField[i][j]+"\t");
            }
            System.out.println();
        }
    }

    private void prepareBoard() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.tabField[i][j] = new Field(0,i,j);
            }
        }
    }

    public int getMovementCounter() {
        return movementCounter;
    }

    public void setMovementCounter(int movementCounter) {
        this.movementCounter = movementCounter;
    }

    public List<Field> getEmptyFields(){
        List<Field> fieldList = new ArrayList<>();
        for (int i = 0; i<dimension; i++){
            for (int j = 0; j<dimension; j++){
                if (this.tabField[i][j].getStatus() == 0){
                    fieldList.add(this.tabField[i][j]);
                }
            }
        }
        return fieldList;
    }

    public void incrementCounter() {
        this.movementCounter++;
    }
}
