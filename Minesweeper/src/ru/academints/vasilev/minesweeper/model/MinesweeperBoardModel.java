package ru.academints.vasilev.minesweeper.model;

import java.util.Arrays;
import java.util.Collections;

public class MinesweeperBoardModel {
    private int boardSize;
    private int bombsCount;
    private Cell[][] cells;

    public MinesweeperBoardModel(int boardSize, int minesCount) {
        this.boardSize = boardSize;
        this.bombsCount = minesCount;
        cells = new Cell[boardSize][boardSize];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
            }
        }

        Integer[] random = new Integer[boardSize * boardSize];
        for (int i = 0; i < random.length; i++) {
            random[i] = i;
        }

        putMines(random);
    }

    private void putMines(Integer[] random) {
        Collections.shuffle(Arrays.asList(random));

        for (int i = bombsCount - 1; i >= 0; i--) {
            int cellToPutMine = random[i];

            int row = cellToPutMine / boardSize;
            int column = cellToPutMine % boardSize;

            cells[row][column].putMine();

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

                    cells[i][j].increaseMinesNearby();
                }
            }
        }
    }

    private void revealConnectedEmptyCells(int row, int column) {
        //проверка на то, что по этим координатам нет бимбы

        for (int i = getStartRow(row); i <= getEndRow(row); i++) {
            for (int j = getStartColumn(column); j <= getEndColumn(column); j++) {
                if (!cells[i][j].isMined() && !cells[i][j].isMarked() && !cells[i][j].isOpened()) {

                    cells[i][j].open();
                }

                if (cells[i][j].getMinesNearbyCount() == 0) {

                    revealConnectedEmptyCells(i, j);
                }
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