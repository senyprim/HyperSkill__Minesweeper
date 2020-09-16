package minesweeper;

import java.util.Arrays;
import java.util.Random;

public class Cells {
    private int width;
    private int height;
    private int countMines;
    private Cell[][] cells;

    public Cells(int width,int height,int countMines){
        this.width=width;
        this.height=height;
        this.countMines=countMines;
        this.cells=new Cell[this.width][this.height];

        for (int x=0;x<width;x++)
            for (int y=0;y<height;y++)
                this.cells[y][x]=new Cell(false);
        Random rnd = new Random();

        while (countMines>0){
            int x=rnd.nextInt(width);
            int y=rnd.nextInt(height);
            if (this.cells[y][x].isMine()) continue;
            this.cells[y][x]=new Cell(true);
            countMines--;
        }
    }

    @Override
    public String toString() {
        return String.join("\n",Arrays.stream(cells).map(row->String.join("",Arrays.stream(row).map(cell->cell.toString()).toArray(String[]::new)))
                .toArray(String[]::new));
    }
}
