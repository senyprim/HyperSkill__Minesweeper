package minesweeper;

import java.util.*;

public class Cells {
    private class Point{
        public final  int x;
        public final  int y;
        public Point(int x,int y){
            this.x=x;this.y=y;
        }
    }
    private int width;
    private int height;
    private int countMines;
    private int truthMark=0;
    private int countMark=0;
    private Cell[][] cells;
    private boolean fail;
    private boolean fill=false;

    public Cells(int width,int height, int countMines){
        this.width=width;
        this.height=height;
        this.countMines=countMines;
        this.fail=false;
        this.cells= new Cell[width][height];
        for (int x=0;x<width;x++)
            for (int y=0;y<height;y++)
                cells[y][x]=new Cell(false);
    }
    public  void fillCells(int xStart,int yStart){
        Random rnd = new Random();
        int counter=countMines;
        while (counter>0){
            int x=rnd.nextInt(width);
            int y=rnd.nextInt(height);
            if (cells[y][x].isMine() || (x==xStart && y==yStart)) continue;
            Cell cell= new Cell(true);
            cell.setMark(cells[y][x].isMark());
            cells[y][x]=cell;
            increaseValues(x,y);
            counter--;
        }
        fill=true;
    }
    public void setMark(int x,int y){
        Cell cell=cells[y][x];
        cell.setMark(!cell.isMark());
        checkMark(x,y);
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public void openCell(int x,int y){
        Cell cell = cells[y][x];
        if (cell.isOpen()) return;
        if (cell.isMine()) {
            setFail();
        }
        cell.open();
    }
    public void open(int x,int y){
        if (!this.fill) fillCells(x,y);
        if (cells[y][x].isOpen()) return;
        openCell(x,y);
        if (cells[y][x].getValue()==0){
            Arrays.stream(getPointNeighborns(x,y))
                    .forEach(point->open(point.x,point.y));
        }
    }
    public boolean isOpen(int x,int y){ return cells[y][x].isOpen();}
    public int getValue(int x,int y){return cells[y][x].getValue(); }
    public boolean isWin(){
        return  (countMark==truthMark && countMark==countMines)
                ||Arrays.stream(cells).flatMap(Arrays::stream).filter(cell->!cell.isMine())
                .allMatch(Cell::isOpen);
    }
    public boolean isFail(){return  fail;}

    private void setFail(){
        this.fail=true;
        Arrays.stream(cells).flatMap(Arrays::stream).filter(Cell::isMine).forEach(Cell::open);
    }
    private Cell[] getNeighborns(int x,int y){
        return Arrays.stream(getPointNeighborns(x,y)).map(point->cells[point.y][point.x]).toArray(Cell[]::new);
    }
    private Point[] getPointNeighborns(int x,int y) {
        List<Point> list = new ArrayList<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int neighborX = x + dx;
                int neighborY = y + dy;
                if ((dx == 0 && dy == 0)
                        || neighborX >= width
                        || neighborY >= height
                        || neighborX < 0
                        || neighborY < 0)
                    continue;
                list.add(new Point(neighborX, neighborY));
            }
        }
        return list.toArray(new Point[0]);
    }
    private void increaseValues(int x,int y){
        if (!cells[y][x].isMine()) return;
        for (Cell cell:getNeighborns(x,y) ) {
         cell.setValue(cell.getValue()+1);
        }
    }
    private void checkMark(int x,int y){
        Cell cell = cells[y][x];
        if (cell.isMark()){
            this.countMark++;
            if (cell.isMine()) this.truthMark++;
        } else {
            this.countMark--;
            if (cell.isMine()) this.truthMark--;
        }
    }


    @Override
    public String toString() {
        return String.join("\n",Arrays.stream(cells).map(row->String.join("",Arrays.stream(row).map(Cell::toString).toArray(String[]::new)))
                .toArray(String[]::new));
    }
}
