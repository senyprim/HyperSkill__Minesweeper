package minesweeper;

public class Cell {
    private static final String MARK_MINE = "X";
    private static final String MARK_SAFE = ".";
    private static final String MARK = "*";

    private boolean  mine;
    private boolean mark;
    private boolean open;
    private int value;

    public Cell(boolean mine){
        this.mine=mine;
        this.open=false;
        this.mark=false;
        this.value =0;
    }

    public boolean isMark() {
        return mark;
    }
    public boolean isMine(){
        return mine;
    }
    public boolean isOpen() {
        return open;
    }
    public int getValue() {
        return isMine()?0: value;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }
    public void setMine(boolean mine){
        this.mine=mine;
    }
    public void open(){
        open=true;
    }
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return isOpen()
                ?isMine()
                    ?MARK_MINE
                    :getValue()==0
                        ?"/"
                        :String.valueOf(getValue())
                :isMark()
                    ?MARK
                    :MARK_SAFE;
    }
}
