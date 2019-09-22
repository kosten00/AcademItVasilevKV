package ru.academits.vasilev.miner;

public class Cell {
    private boolean closed;
    private boolean isMined;
    private boolean isMarked;
    private int minesNearby;

    public Cell() {
        closed = true;
        isMined = false;
        isMarked = false;
        minesNearby = 0;
    }

    public void open() {
        closed = false;
    }

    public void putMine() {
        isMined = true;
    }

    public void putMark() {
        isMarked = true;
    }

    public void removeMark() {
        isMarked = false;
    }

    public void increaseMinesNearby() {
        this.minesNearby++;
    }

    public int getMinesNearby() {
        return minesNearby;
    }

    public boolean isMined() {
        return isMined;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public boolean isClosed() {
        return closed;
    }
}