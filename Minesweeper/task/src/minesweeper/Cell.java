package minesweeper;

public class Cell {
    private static final String MINE_MARK = "X";
    private static final String SAFE_MARK = ".";
    final private boolean  mine;
    public Cell(boolean mine){
        this.mine=mine;
    }
    public boolean isMine(){
        return mine;
    }

    @Override
    public String toString() {
        return mine?MINE_MARK:SAFE_MARK;
    }
}
