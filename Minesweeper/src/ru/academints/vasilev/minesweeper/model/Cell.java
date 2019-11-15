package ru.academints.vasilev.minesweeper.model;

public class Cell {
    private boolean isOpened;
    private boolean isMined;
    private boolean isMarked;
    private int bombsNearbyCount;

    public Cell() {
        isOpened = false;
        isMined = false;
        isMarked = false;
        bombsNearbyCount = 0;
    }

    public void open() {
        isOpened = true;
    }

    public void putBomb() {
        isMined = true;
    }

    public void putMark() {
        isMarked = true;
    }

    public void removeMark() {
        isMarked = false;
    }

    public void increaseBombsNearby() {
        bombsNearbyCount++;
    }

    public int getBombsNearbyCount() {
        return bombsNearbyCount;
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

        return "" + getBombsNearbyCount();
    }
}