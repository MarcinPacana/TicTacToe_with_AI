package pl.game;

import java.util.Scanner;

public abstract class PlayerBase {

    private String name;
    protected int symbol;
    private Scanner scanner;

    public PlayerBase(String name) {
        this.scanner = new Scanner(System.in);
        this.name = name;
    }

    public abstract int[] makeAMove();
    public abstract int[] makeAMove(pl.game.Board board);
    public abstract void setUserInterface(UserInterface ui);

    public static PlayerBase getOponent(PlayerBase player){
        return (Game.firstPlayer.getSymbol() == player.getSymbol()) ? Game.secondPlayer : Game.firstPlayer;
    }

    public String getName() {
        return name;
    }

    public int getSymbol() {
        return symbol;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
    }
}
