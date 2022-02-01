package pl.game;

import java.util.List;

public class ArtificialIntelligence {

    public pl.game.GameManager gameManager;
    private static final int minValue = Integer.MIN_VALUE;
    private static final int maxValue = Integer.MAX_VALUE;
    public static int callCounter = 0;

    public ArtificialIntelligence(pl.game.GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public pl.game.ResultMinMax minMax(pl.game.Board board, pl.game.PlayerBase player) {
        return minMax(board, 1, player, null,minValue,maxValue);
    }

    public pl.game.ResultMinMax minMax(pl.game.Board board, int depth, pl.game.PlayerBase player,
                                       int[] lastMove,int alpha, int beta) {
        callCounter++;
        pl.game.Board clonedBoard = board.clone();
        int maxEval = minValue;
        int minEval = maxEval;

        int best;
        Field bestField = new Field();
        int scoring = 0;

        List<Field> emptyFields = clonedBoard.getEmptyFields();
        scoring = evaluate(clonedBoard);

        if (scoring != 0) {
            return new pl.game.ResultMinMax(scoring, bestField);
        }

        if (emptyFields.isEmpty()) {
            return new pl.game.ResultMinMax(scoring, bestField);
        }

        // jezeli gracz jest maxymalizujacy
        if (player.symbol == 2) {
            best = minValue;
            for (Field filed : emptyFields) {
                clonedBoard.setSymbol(filed.getRow(), filed.getCol(), player.symbol);
                int[] move = {filed.getRow(), filed.getCol()};
                pl.game.ResultMinMax resultMinMax = minMax(clonedBoard, depth + 1,
                        pl.game.PlayerBase.getOponent(player), move,alpha,beta);

                int eval = resultMinMax.getScore();

                if (eval > best) {
                    bestField.setRow(filed.getRow());
                    bestField.setCol(filed.getCol());
                    best = eval;
                }
                alpha = Math.max(alpha,eval);
                clonedBoard.setSymbol(filed.getRow(), filed.getCol(), 0);
                if (beta < alpha){

                    break;
                }
            }
            return new pl.game.ResultMinMax(best, bestField);
            // jezeli gracz jest minimalizujacy
        } else {
            best = maxValue;
            for (Field filed : emptyFields) {
                clonedBoard.setSymbol(filed.getRow(), filed.getCol(), player.symbol);
                int[] move = {filed.getRow(), filed.getCol()};
                pl.game.ResultMinMax resultMinMax = minMax(clonedBoard, depth + 1,
                        pl.game.PlayerBase.getOponent(player), move,alpha,beta);

                int eval = resultMinMax.getScore();
                if (eval < best) {
                    bestField.setRow(filed.getRow());
                    bestField.setCol(filed.getCol());
                    best = eval;
                }
                beta = Math.min(beta,eval);
                clonedBoard.setSymbol(filed.getRow(), filed.getCol(), 0);
                if (beta <= alpha){

                    break;
                }
            }
            return new pl.game.ResultMinMax(best, bestField);
        }
    }

    public int evaluate(pl.game.Board board) {
        Field[][] tabField = board.getTabField();
        int counter = 0;

        // Check by rows
        for (int i = 0; i < tabField.length; i++) {
            for (int j = 0; j < tabField.length - 1; j++) {
                if (tabField[i][j].getStatus() == tabField[i][j + 1].getStatus() && tabField[i][j].getStatus() != 0) {
                    counter++;
                } else counter = 0;
                if (counter == 2 && tabField[i][j].getStatus() != 0) {
                    return tabField[i][j].getStatus() == 2 ? +10 : -10;
                }
            }
        }

        // Check by columns
        for (int i = 0; i < tabField.length; i++) {
            for (int j = 0; j < tabField.length - 1; j++) {
                if (tabField[j][i].getStatus() == tabField[j + 1][i].getStatus() && tabField[j][i].getStatus() != 0) {
                    counter++;
                } else counter = 0;
                if (counter == 2 && tabField[i][j].getStatus() != 0) {
                    return tabField[i][j].getStatus() == 2 ? +10 : -10;
                }
            }
        }

        // check by slants
        int winR = winByRightSlant(board);
        if (winR != 0) {
            return winR == 2 ? +10 : -10;
        }
        int winL = winByLeftSlant(board);
        if (winL != 0) {
            return winL == 2 ? +10 : -10;
        }
        return 0;
    }

    private int winByRightSlant(pl.game.Board board) {
        Field[][] tabField = board.getTabField();
        int dimension = tabField.length;
        int counter = 0;

        int start = dimension - 2;
        int minStaart = start - 2 * start;
        int rowStart = start - 1;

        for (int i = rowStart; i > minStaart; i--) {
            if (i >= 0) {
                for (int j = 0; j < dimension - i - 1; j++) {
                    if (tabField[i + j][j].getStatus() == tabField[i + j + 1][j + 1].getStatus() && tabField[i + j][j].getStatus() != 0) { // dodac warunek   &&status != 0
                        counter++;
                    } else counter = 0;
                    if (counter == 2) {
                        return tabField[i + j][j].getStatus();
                    }
                }

            } else {
                for (int j = 0; j < dimension + i - 1; j++) {
                    if (tabField[j][j - i].getStatus() == tabField[j + 1][j - i + 1].getStatus() && tabField[j][j - i].getStatus() != 0) { // dodac warunek   &&status != 0
                        counter++;
                    } else counter = 0;
                    if (counter == 2) {
                        return tabField[j][j - i].getStatus();
                    }
                }
            }
        }
        return 0;
    }

    private int winByLeftSlant(pl.game.Board board) {
        Field[][] tabField = board.getTabField();
        int dimension = tabField.length;
        int counter = 0;

        int start = dimension - 2;
        int minStaart = start - 2 * start;
        int rowStart = start - 1;

        for (int i = rowStart; i > minStaart; i--) {
            if (i >= 0) {
                for (int j = 0; j < dimension - i - 1; j++) {
                    if (tabField[i + j][dimension - 1 - j].getStatus() == tabField[i + j + 1][dimension - 1 - j - 1].getStatus() && tabField[i + j][dimension - 1 - j].getStatus() != 0) {
                        counter++;
                    } else counter = 0;
                    if (counter == 2) {
                        return tabField[i + j][dimension - 1 - j].getStatus();
                    }
                }

            } else {
                for (int j = 0; j < dimension + i-1; j++) {
                    if (tabField[j][dimension + i - 1 - j].getStatus() == tabField[j + 1][dimension + i - 1 - j - 1].getStatus() && tabField[j][dimension + i - 1 - j].getStatus() != 0) {
                        counter++;
                    } else counter = 0;
                    if (counter == 2) {
                        return tabField[j][dimension + i - 1 - j].getStatus();
                    }
                }
            }
        }
        return 0;
    }

    static int evaluate2(pl.game.Board board) {
        Field[][] b = board.getTabField();
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++) {
            if (b[row][0].getStatus() == b[row][1].getStatus() && b[row][1].getStatus() == b[row][2].getStatus()) {
                if (b[row][0].getStatus() == 2)
                    return +10;
                else if (b[row][0].getStatus() == 1)
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++) {
            if (b[0][col].getStatus() == b[1][col].getStatus() && b[1][col].getStatus() == b[2][col].getStatus()) {
                if (b[0][col].getStatus() == 2)
                    return +10;
                else if (b[0][col].getStatus() == 1)
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b[0][0].getStatus() == b[1][1].getStatus() && b[1][1].getStatus() == b[2][2].getStatus()) {
            if (b[0][0].getStatus() == 2)
                return +10;
            else if (b[0][0].getStatus() == 1)
                return -10;
        }
        if (b[0][2].getStatus() == b[1][1].getStatus() && b[1][1].getStatus() == b[2][0].getStatus()) {
            if (b[0][2].getStatus() == 2)
                return +10;
            else if (b[0][2].getStatus() == 1)
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }
}
