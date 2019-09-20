package ru.academits.vasilev.miner;

public class Cell {
    private boolean isOpened;
    private boolean isMined;
    private boolean isMarked;
    private int number;

    public Cell() {
        isMined = false;
        isMarked = false;
        isOpened = false;
        number = 0;
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

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
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