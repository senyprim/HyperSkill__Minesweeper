package minesweeper;

public class Cell {
    private static final String MINE_MARK = "X";
    private static final String SAFE_MARK = ".";
    final private boolean  mine;
    private boolean open;
    private int neighborsMine;
    public Cell(boolean mine){
        this.mine=mine;
        this.open=false;
        this.neighborsMine=0;
    }
    public boolean isMine(){
        return mine;
    }
    public void open(){
        open=true;
    }

    public int getNeighborsMine() {
        return neighborsMine;
    }

    public void setNeighborsMine(int neighborsMine) {
        this.neighborsMine = neighborsMine;
    }

    @Override
    public String toString() {
        return isMine()
                ?MINE_MARK
                :getNeighborsMine()>0
                    ?String.valueOf(getNeighborsMine())
                    :SAFE_MARK;

    }
}
