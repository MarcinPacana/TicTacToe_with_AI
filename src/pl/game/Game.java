package pl.game;

public class Game {

    public UserInterface userInterface;
    public pl.game.Board board;
    public static pl.game.PlayerBase firstPlayer;
    public static pl.game.PlayerBase secondPlayer;
    private pl.game.GameManager gameManager;
    private pl.game.PlayerBase actualPlayer;
    private boolean win;

    public Game() {
        this.userInterface = new UserInterface();
        this.gameManager = new pl.game.GameManager();
    }

    public void play() {
        prepareGame();
        startGame();
    }

    private void startGame() {
        actualPlayer = firstPlayer;
        userInterface.showMessage("Imie aktualnego gracza to: "+actualPlayer.getName());
        while (true){
            int[] playerMovement = getPlayersMove(actualPlayer);
            pl.game.PlayerBase playerBase = gameManager.checkWins(actualPlayer , this.board, playerMovement);

            if (playerBase != null){
                userInterface.showWin();
            }

            boolean draw = gameManager.checkDraw(board.getMovementCounter(), board.getDimension());

            if ((playerBase != null || draw)){
                userInterface.showFinalMessage();
                win = true;
                break;
            }

            actualPlayer = actualPlayer.symbol == firstPlayer.symbol ? secondPlayer : firstPlayer;
        }
    }

    private int[] getPlayersMove(pl.game.PlayerBase player){
        int[] playerMovement = player.makeAMove(this.board);
        if (!board.checkValueMovement(playerMovement)){
            board.drawBoard();
            userInterface.showMessage("Wskazany miejscie wykracza poza wielkosc planszy");
            getPlayersMove(player);
        }else if ( !board.checkCorrectMovement(playerMovement)){
            board.drawBoard();
            userInterface.showMessage("To pole jest juz zajete");
            getPlayersMove(player);
        }else {
            board.setSymbol(playerMovement[0],playerMovement[1],player.symbol);
            userInterface.showMessage("Ruch ["+playerMovement[0]+","+playerMovement[1]+"] zostal wykonany");
            userInterface.drawBoard(board);
        }
        return playerMovement;
    }

    private void prepareGame() {
        this.board = userInterface.welcomeGame();
        setTypeOfGame();
        userInterface.drawBoard(board);
    }

    private void setTypeOfGame() {
        int typeOfGame = userInterface.selectTypeGame();
        if (typeOfGame == 1) {
            createTwoHumanPlayers();
        } else if (typeOfGame == 2) {
            createHumanAndComputerPlayers();
        }
    }

    private void createTwoHumanPlayers() {
        firstPlayer = new pl.game.HumanPlayer(userInterface.getNamePlayer(),userInterface);
        firstPlayer.setSymbol(1);

        secondPlayer = new pl.game.HumanPlayer(userInterface.getNamePlayer(),userInterface);
        secondPlayer.setSymbol(2);
    }

    private void createHumanAndComputerPlayers() {
        firstPlayer = new pl.game.HumanPlayer(userInterface.getNamePlayer(),userInterface);
        firstPlayer.setSymbol(1);

        secondPlayer = new pl.game.ComputerPlayer(gameManager);
        secondPlayer.setUserInterface(userInterface);
        secondPlayer.setSymbol(2);
    }

}
