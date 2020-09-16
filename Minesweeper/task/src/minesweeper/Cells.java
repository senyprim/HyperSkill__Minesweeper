package minesweeper;

import java.util.*;

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
            setNeighborns(x,y);
            countMines--;
        }
    }
    public Cell[][] getCells(){
        return this.cells;
    }
    private Cell[] getNeighborns(int x,int y){
        List<Cell> neighbor = new ArrayList<>();
        for(int dx=-1;dx<=1;dx++){
            for(int dy=-1;dy<=1;dy++){
                int neighborX=x+dx;
                int neighborY=y+dy;
                if ((dx==0 && dy==0)
                        || neighborX>=width
                        || neighborY>=height
                        || neighborX<0
                        || neighborY<0)
                    continue;

                neighbor.add(this.cells[neighborY][neighborX]);
            }
        }
        return neighbor.toArray(new Cell[0]);
    }
    private void setNeighborns(int x,int y){
        if (!cells[y][x].isMine()) return;
        for (Cell cell:getNeighborns(x,y) ) {
         cell.setNeighborsMine(cell.getNeighborsMine()+1);
        }
    }

    @Override
    public String toString() {
        return String.join("\n",Arrays.stream(cells).map(row->String.join("",Arrays.stream(row).map(Cell::toString).toArray(String[]::new)))
                .toArray(String[]::new));
    }

}
