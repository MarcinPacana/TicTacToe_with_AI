package pl.game;

import java.util.Scanner;

public class UserInterface {

    private final Scanner scannerInt;
    private final Scanner scannerString;

    public UserInterface() {
        this.scannerInt = new Scanner(System.in);
        this.scannerString = new Scanner(System.in);
    }

    public pl.game.Board welcomeGame() {
        System.out.println("\t Witaj W GRZE KÓŁKO-KRZYŻYK");
        System.out.println("------------------------------------");
        System.out.print("Podaj wymiar planszy na jakiech chcesz zagrać: ");
        int dimension = scannerInt.nextInt();
        return new pl.game.Board(dimension);
    }

    public String getNamePlayer() {
        System.out.print("Podaj imię pierwszego gracza: ");
        return scannerString.nextLine();
    }

    public int selectTypeGame() {
        System.out.println("------------------------------------");
        System.out.println("Wybierz rodzaj gry:");
        System.out.println("1 - Multiplayer");
        System.out.println("2 - Gra z komputerem");
        System.out.print("Twoj wybór to: ");
        return scannerInt.nextInt();
    }

    public void drawBoard(pl.game.Board board) {
        System.out.println("\n");
        board.drawBoard();
        System.out.println("\n");
    }

    public void showMessageWarn(String message){
        System.out.println("\nUWAGA: "+message+"\n");
    }

    public int[] getMove(){
        System.out.print("Podaj index WIERSZA: ");
        int rowIndex = scannerInt.nextInt();
        System.out.print("Podaj index KOLUMNY: ");
        int column = scannerInt.nextInt();
        int[] tab = new int[]{rowIndex,column};
        return tab;
    }

    public int[] getMove(String name){
        System.out.println("\n");
        System.out.println(name+", Twoj ruch");
        return getMove();
    }

    public void showFinalMessage() {
        System.out.println("Koniec gry");
    }

    public void showWin() {
        System.out.println("Uczestnik wygral w grze");
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }
}
