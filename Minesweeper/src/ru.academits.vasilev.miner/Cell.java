package ru.academits.vasilev.miner;

public class Cell {
    private boolean isOpened;
    private boolean isMined;
    private boolean isMarked;
    private int MinesNearby;

    public Cell() {
        isOpened = false;
        isMined = false;
        isMarked = false;
        MinesNearby = 0;
    }

    public void open() {
        isOpened = true;
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
        this.MinesNearby++;
    }

    public int getMinesNearby() {
        return MinesNearby;
    }

    public boolean isMined() {
        return isMined;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public boolean isOpened() {
        return isOpened;
    }
}