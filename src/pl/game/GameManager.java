package pl.game;

public class GameManager {

    public PlayerBase checkWins(PlayerBase player, Board board, int[] playerMovement) {
        boolean winByRow = checkByRows(playerMovement[0], playerMovement[1], player.symbol, board);
        boolean winByCol = checkByCol(playerMovement[0], playerMovement[1], player.symbol, board);
        boolean winBySlant = checkBySlant(playerMovement[0], playerMovement[1], player.symbol, board);
        if (winByRow || winByCol || winBySlant) {
            return player;
        }
        return null;
    }

    public boolean checkDraw(int movementCounter, int dimension) {
        if (movementCounter >= dimension * dimension) {
            return true;
        } else return false;
    }

    private boolean checkByRows(int row, int col, int symbol, Board board) {
        int winCounter = 0;
        Field[][] tabField = board.getTabField();
        int dimension = board.getDimension();

        int startCol = col - 2;
        int endCol = col + 2;

        if (startCol < 0) {
            startCol = 0;
        }
        if (endCol > dimension - 1) {
            endCol = dimension - 1;
        }

        for (int i = startCol; i <= endCol; i++) {

            if (tabField[row][i].getStatus() == symbol) {
                winCounter++;
            } else {
                winCounter = 0;
            }
            if (winCounter >= 3) {
                return true;
            }
        }
        return false;
    }

    private boolean checkByCol(int row, int col, int symbol, Board board) {
        int winCounter = 0;
        Field[][] tabField = board.getTabField();
        int dimension = board.getDimension();

        int startRow = row - 2;
        int endRow = row + 2;

        if (startRow < 0) {
            startRow = 0;
        }


        if (endRow > dimension - 1) {
            endRow = dimension - 1;
        }


        for (int i = startRow; i <= endRow; i++) {
            if (tabField[i][col].getStatus() == symbol) {
                winCounter++;
            } else {
                winCounter = 0;
            }
            if (winCounter >= 3) {
                return true;
            }
        }
        return false;
    }

    private boolean checkBySlant(int row, int col, int symbol, Board board) {
        Field[][] tabField = board.getTabField();
        int dimension = board.getDimension();
        int countSymbol = 0;
        int rowStart = row - 2;
        if (rowStart < 0) {
            rowStart = 0;
        }
        int colStart = col - 2;
        if (colStart < 0) {
            colStart = 0;
        }

        for (int i = 0; i < 5; i++) {
            int status = tabField[rowStart][colStart].getStatus();
            if (status == symbol) {
                countSymbol++;
            } else {
                countSymbol = 0;
            }
            if (countSymbol == 3) {
                return true;
            }
            rowStart++;
            colStart++;
            if (rowStart > dimension - 1 || colStart > dimension - 1) {
                return false;
            }
        }

        rowStart = row - 2;
        if (rowStart < 0) {
            rowStart = 0;
        }

        colStart = col + 2;
        if (colStart > dimension - 1) {
            colStart = dimension - 1;
        }

        for (int i = 0; i < 5; i++) {
            int status = tabField[rowStart][colStart].getStatus();
            if (status == symbol) {
                countSymbol++;
            } else {
                countSymbol = 0;
            }
            if (countSymbol == 3) {
                return true;
            }
            rowStart++;
            colStart--;
            if (rowStart > dimension - 1 || colStart < 0) {
                return false;
            }
        }
        return false;
    }
}
