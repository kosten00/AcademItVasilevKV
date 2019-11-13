package ru.academints.vasilev.minesweeper.view;

import ru.academints.vasilev.minesweeper.model.MinesweeperBoardModel;

import javax.swing.*;
import java.awt.*;

public class MinesweeperVisibleBoard extends JPanel {
    private MinesweeperBoardModel boardModel;

    private int width;
    private int height;

    private int spaceBetweenCells = 5;

    private int cellSize = 50;

    public MinesweeperVisibleBoard(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);
        g.fillRect(0,0, width, height);
        g.setColor(Color.white);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                g.fillRect(spaceBetweenCells + i * cellSize, spaceBetweenCells + j * cellSize, cellSize - spaceBetweenCells, cellSize - spaceBetweenCells);

            }
        }

    }
}