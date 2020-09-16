package minesweeper;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommandShell {
    private Cells cells;
    private Scanner scanner;
    public CommandShell(Cells cells,Scanner scanner){
        this.cells=cells;
        this.scanner=scanner;
    }
    public void run(){
        System.out.println(printCells());
        while (!cells.isWin()){
            System.out.print("Set/delete mines marks (x and y coordinates): ");
            String[] input=scanner.nextLine().split("\\s+");
            int x=Integer.parseInt(input[0])-1;
            int y=Integer.parseInt(input[1])-1;
            if (cells.getNeighbornMine(x,y)!=0){
                System.out.println("There is a number here!");
                continue;
            }
            cells.setMark(x,y);
            System.out.println(printCells());
        }
        System.out.println("Congratulations! You found all mines!");


    }
    public String printCells(){
        return "\n"+printHeader()+"\n"+printBody();
    }

    private String printBody() {
        StringBuilder stb = new StringBuilder();
        stb.append("-|"+"-".repeat(cells.getWidth())+"|\n");
        String[] rows = cells.toString().split("\n");
        for(int i=0;i<rows.length;i++){
            stb.append(i+1+"|"+rows[i]+"|\n");
        }
        stb.append("-|"+"-".repeat(cells.getWidth())+"|");
        return stb.toString();
    }

    private String printHeader() {
        return " |"
                +IntStream.range(1,cells.getWidth()+1).mapToObj(String::valueOf).collect(Collectors.joining())
                +"|";
    }
}
