package minesweeper;

public class Cell {
    private static final String MARK_MINE = "X";
    private static final String MARK_SAFE = ".";
    private static final String MARK = "*";
    final private boolean  mine;
    private boolean mark;
    private boolean open;
    private int neighborsMine;

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public Cell(boolean mine){
        this.mine=mine;
        this.open=false;
        this.mark=false;
        this.neighborsMine=0;
    }
    public boolean isMine(){
        return mine;
    }
    public void open(){
        open=true;
    }

    public int getNeighborsMine() {
        return isMine()?0:neighborsMine;
    }

    public void setNeighborsMine(int neighborsMine) {
        this.neighborsMine = neighborsMine;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }


    @Override
    public String toString() {
        if (mark) return MARK;
        return getNeighborsMine()>0
                ?String.valueOf(getNeighborsMine())
                :MARK_SAFE;
    }
}
