package ru.academits.vasilev.miner;

import java.util.Arrays;
import java.util.Random;

public class Board {
    private int boardSize;
    private int minesCount;
    private Cell[][] cells = new Cell[boardSize][boardSize]; /// изза дефолтных значений переменных не пишется нифига

    private void putMines() {
        Random random = new Random();

        for (int i = minesCount; i >= 0; i--) {
            int cellToPutMine = random.nextInt(boardSize * 2);

            int row = cellToPutMine / boardSize;
            int column = cellToPutMine % boardSize;

            int j = row - 1;
            int k = column - 1;

            if (row == 0) {
                j = row;
            }

            if (column == 0) {
                k = column;
            }

            for (; j <= row + 1; j++) {
                for (; k <= column + 1; k++) {
                    if (j == row && k == column) {
                        cells[j][k].putMine();

                        continue;
                    }

                    cells[j][k].increaseMinesNearby();
                }
            }
        }
    }

    public Board(int boardSize, int minesCount) {
        this.boardSize = boardSize;
        this.minesCount = minesCount;

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
            }
        }

        putMines();
    }

    @Override
    public String toString() {
        return Arrays.deepToString(cells);
    }
}