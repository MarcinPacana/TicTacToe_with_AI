package pl.game;

public class ComputerPlayer extends PlayerBase{

    ArtificialIntelligence artificialIntelligence;

    public ComputerPlayer(pl.game.GameManager gameManager) {
        super("KOMPUTER");
        this.artificialIntelligence = new ArtificialIntelligence(gameManager);
    }

    @Override
    public int[] makeAMove() {
        return null;
    }

    @Override
    public int[] makeAMove(pl.game.Board board) {
        ResultMinMax resultMinMax = artificialIntelligence.minMax(board, this);
        return new int[]{resultMinMax.getField().getRow(),resultMinMax.getField().getCol()};
    }

    @Override
    public void setUserInterface(UserInterface ui) {
    }
}
