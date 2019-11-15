package ru.academints.vasilev.minesweeper.model;

import java.util.Arrays;
import java.util.Collections;

public class MinesweeperModel {
    private int boardSize;
    private int bombsCount;
    public Cell[][] cells;

    public MinesweeperModel(int boardSize, int minesCount) {
        this.boardSize = boardSize;
        this.bombsCount = minesCount;
        cells = new Cell[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                cells[i][j] = new Cell();
            }
        }

        Integer[] random = new Integer[boardSize * boardSize];
        for (int i = 0; i < random.length; i++) {
            random[i] = i;
        }

        putMines(random);
    }

    public void getCell(int i, int j) {

    }

    private void putMines(Integer[] random) {
        Collections.shuffle(Arrays.asList(random));

        for (int i = bombsCount - 1; i >= 0; i--) {
            int cellToPutMine = random[i];

            int row = cellToPutMine / boardSize;
            int column = cellToPutMine % boardSize;

            cells[row][column].putBomb();

            countNearbyMines(row, column);
        }
    }

    private int getStartRow(int row) {
        int startRow = row - 1;

        if (startRow < 0) {
            startRow = row;
        }

        return startRow;
    }

    private int getStartColumn(int column) {
        int startColumn = column - 1;

        if (startColumn < 0) {
            startColumn = column;
        }

        return startColumn;
    }

    private int getEndRow(int row) {
        int endRow = row + 1;

        if (endRow >= cells.length) {
            endRow = row;
        }

        return endRow;
    }

    private int getEndColumn(int column) {
        int endColumn = column + 1;

        if (endColumn >= cells[0].length) {
            endColumn = column;
        }

        return endColumn;
    }

    private void countNearbyMines(int row, int column) {
        for (int i = getStartRow(row); i <= getEndRow(row); i++) {
            for (int j = getStartColumn(column); j <= getEndColumn(column); j++) {
                if (!cells[i][j].isMined()) {

                    cells[i][j].increaseBombsNearby();
                }
            }
        }
    }

    public void revealConnectedEmptyCells(int row, int column) {
        int startRow = getStartRow(row);
        int startColumn = getStartColumn(column);

        int endRow = getEndRow(row);
        int endColumn = getEndColumn(column);

        for (int i = startRow; i <= endRow; i++) {
            for (int j = startColumn; j <= endColumn; j++) {
                if (!cells[i][j].isMined() && !cells[i][j].isMarked() && !cells[i][j].isOpened()) {
                    cells[i][j].open();
                }

//                if (cells[i][j].getBombsNearbyCount() == 0) {
//
//                    revealConnectedEmptyCells(i, j);
//                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Cell[] cell : cells) {
            sb.append(Arrays.toString(cell)).append(System.lineSeparator());
        }

        return sb.toString();
    }
}