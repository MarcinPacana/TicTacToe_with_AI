package pl.game;

public class HumanPlayer extends PlayerBase {

    private UserInterface userInterface;

    public HumanPlayer(String name, UserInterface userInterface) {
        super(name);
        this.userInterface = userInterface;
    }

    public String getName() {
        return super.getName();
    }

    @Override
    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public int[] makeAMove() {
        return userInterface.getMove(super.getName());
    }

    @Override
    public int[] makeAMove(pl.game.Board board) {
        return makeAMove();
    }
}
