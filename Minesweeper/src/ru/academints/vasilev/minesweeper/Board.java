package ru.academints.vasilev.minesweeper;

import java.util.Arrays;
import java.util.Collections;

public class Board {
    private int boardSize;
    private int minesCount;
    private Cell[][] cells;

    public Board(int boardSize, int minesCount) {
        this.boardSize = boardSize;
        this.minesCount = minesCount;
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

        for (int i = minesCount - 1; i >= 0; i--) {
            int cellToPutMine = random[i];

            int row = cellToPutMine / boardSize;
            int column = cellToPutMine % boardSize;

            cells[row][column].putMine();

            countNearbyMines(row, column);
        }
    }

    private void countNearbyMines(int row, int column) {
        int startRow = row - 1;
        int startColumn = column - 1;
        int endRow = row + 1;
        int endColumn = column + 1;

        int i = startRow;
        int j = startColumn;

        if (startRow < 0) {
            i = row;
        }

        if (startColumn < 0) {
            j = column;
        }

        if (endRow >= cells.length) {
            endRow = row;
        }

        if (endColumn >= cells[row].length) {
            endColumn = column;
        }

        for (; i <= endRow; i++) {
            for (; j <= endColumn; j++) {
                cells[i][j].increaseMinesNearby();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;

        for (Cell[] cell : cells) {
            sb.append(Arrays.toString(cell)).append(System.lineSeparator());
            i++;
        }

        return sb.toString();
    }
}