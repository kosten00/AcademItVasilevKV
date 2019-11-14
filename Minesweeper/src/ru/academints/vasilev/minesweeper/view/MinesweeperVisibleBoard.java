package ru.academints.vasilev.minesweeper.view;

import ru.academints.vasilev.minesweeper.model.MinesweeperBoardModel;

import javax.swing.*;
import java.awt.*;

public class MinesweeperVisibleBoard extends JPanel {
    private MinesweeperBoardModel boardModel;

    private int width;
    private int height;

    private int spaceBetweenCells;
    private int cellSize;

    public MinesweeperVisibleBoard(int width, int height) {
        this.width = width;
        this.height = height;

        spaceBetweenCells = (width + height) / 200;

        cellSize = ((width + height) / 20);
    }

    @Override
    public void paintComponent(Graphics g) {

        System.out.println(spaceBetweenCells);
        System.out.println(cellSize);

        super.paintComponent(g);

        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.white);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                g.fillRect(spaceBetweenCells + i * cellSize, spaceBetweenCells + j * cellSize, cellSize - spaceBetweenCells, cellSize - spaceBetweenCells);

            }
        }

    }
}