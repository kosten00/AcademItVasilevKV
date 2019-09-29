package ru.academints.vasilev.minesweeper.gui;

//GridLayout
//По умолчанию поле размером 9x9
//количество мин 10.

import ru.academints.vasilev.minesweeper.Board;

import javax.swing.*;
import java.awt.*;

public class VisualBoard extends JFrame {
    private int boardSize;
    private int minesCount;
    private final int DEFAULT_SIZE = 9;
    private final int DEFAULT_BOMBS_COUNT = 10;
    private JButton cellButton;

    private Board board;

    public void setBoardSize(int size) {
        this.boardSize = size;
    }

    public void setMinesCount(int minesCount) {
        this.minesCount = minesCount;
    }

    public VisualBoard(int size, int bombsCount) {
        board = new Board(size, bombsCount);

        setLayout(new GridLayout(size, size));
    }
}