package ru.academints.vasilev.minesweeper.view;

import ru.academints.vasilev.minesweeper.model.MinesweeperBoardModel;

import javax.swing.*;
import java.awt.*;

public class MinesweeperVisibleBoard extends JFrame {
    private MinesweeperBoardModel boardModel;

    private int boardSize = 500;

    private int spaceBetweenCells = 5;

    private int cellSize = 10;

    public MinesweeperVisibleBoard() {
        boardModel = new MinesweeperBoardModel(5, 5);

        setTitle("Board testing");
        setSize(boardSize, boardSize);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        //g.fillRect(0, 0, boardSize, boardSize);

        //g.setColor(Color.white);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                g.fillRect(spaceBetweenCells + i * cellSize, spaceBetweenCells + j * cellSize + cellSize, cellSize - spaceBetweenCells, cellSize - spaceBetweenCells);

                //g.fillRect();
            }
        }
    }
}