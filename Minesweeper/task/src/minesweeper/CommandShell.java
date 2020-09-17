package minesweeper;

import java.util.Objects;
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
        while (!(cells.isWin()|| cells.isFail())){
            System.out.print("Set/unset mines marks or claim a cell as free: ");
            String[] input=scanner.nextLine().split("\\s+");
            int x=Integer.parseInt(input[0])-1;
            int y=Integer.parseInt(input[1])-1;
            String command=input[2];
            if (Objects.equals(command,"free")) cells.open(x,y);
            if (Objects.equals(command,"mine")) cells.setMark(x,y);

            System.out.println(printCells());
        }
        System.out.println(
                cells.isWin()
                        ?"Congratulations! You found all mines!"
                        :"You stepped on a mine and failed!");
    }
    public String printCells(){
        return "\n"+printHeader()+"\n"+printBody();
    }

    private String printBody() {
        StringBuilder stb = new StringBuilder();
        stb.append("-|");stb.append("-".repeat(cells.getWidth()));stb.append("|\n");
        String[] rows = cells.toString().split("\n");
        for(int i=0;i<rows.length;i++){
            stb.append(i+1);stb.append("|");stb.append(rows[i]);stb.append("|\n");
        }
        stb.append("-|");stb.append("-".repeat(cells.getWidth()));stb.append("|");
        return stb.toString();
    }

    private String printHeader() {
        return " |"
                +IntStream.range(1,cells.getWidth()+1).mapToObj(String::valueOf).collect(Collectors.joining())
                +"|";
    }
}
