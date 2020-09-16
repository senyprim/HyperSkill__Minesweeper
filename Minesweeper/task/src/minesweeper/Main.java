package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("How many mines do you want on the field? ");
        int countMines = Integer.parseInt(new Scanner(System.in).nextLine());
        CommandShell shell = new CommandShell(new Cells(9,9,countMines),new Scanner(System.in));
        shell.run();
    }
}
