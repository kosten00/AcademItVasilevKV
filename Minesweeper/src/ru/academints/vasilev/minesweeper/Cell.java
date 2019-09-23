package ru.academints.vasilev.minesweeper;

public class Cell {
    private boolean isOpened;
    private boolean isMined;
    private boolean isMarked;
    private int minesNearbyCount;

    public Cell() {
        isOpened = false;
        isMined = false;
        isMarked = false;
        minesNearbyCount = 0;
    }

    public void open() {
        isOpened = false;
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
        minesNearbyCount++;
    }

    public int getMinesNearbyCount() {
        return minesNearbyCount;
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

    @Override
    public String toString() {
        if (isMined) {
            return "B";
        }

        return "" + getMinesNearbyCount();
    }
}