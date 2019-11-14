package ru.academints.vasilev.minesweeper.view;

import ru.academints.vasilev.minesweeper.model.MinesweeperBoardModel;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

//public class MinesweeperVisibleBoard extends JPanel {
//    private MinesweeperBoardModel boardModel;
//
//    private int width;
//    private int height;
//
//    private int spaceBetweenCells;
//    private int cellSize;
//
//    public MinesweeperVisibleBoard(int width, int height) {
//        this.width = width;
//        this.height = height;
//
//        spaceBetweenCells = (width + height) / 200;
//
//        cellSize = ((width + height) / 20);
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//
//        System.out.println(spaceBetweenCells);
//        System.out.println(cellSize);
//
//        super.paintComponent(g);
//
//        g.setColor(Color.black);
//        g.fillRect(0, 0, width, height);
//        g.setColor(Color.white);
//
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                g.fillRect(spaceBetweenCells + i * cellSize, spaceBetweenCells + j * cellSize, cellSize - spaceBetweenCells, cellSize - spaceBetweenCells);
//
//            }
//        }
//    }
//}

class MinesweeperVisibleBoard extends JPanel {
    private ArrayList<Point> fillCells;

    public MinesweeperVisibleBoard() {
        fillCells = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Point fillCell : fillCells) {
            int cellX = 10 + (fillCell.x * 10);
            int cellY = 10 + (fillCell.y * 10);
            //g.setColor(Color.RED);
            g.fillRect(cellX, cellY, 10, 10);
        }
        //g.setColor(Color.BLACK);
        g.drawRect(10, 10, 555 - 10, 570 - 10);

        for (int i = 10; i <= 555 - 10; i += 10) {
            g.drawLine(i, 10, i, 510);
        }

        for (int i = 10; i <= 570 - 10; i += 10) {
            g.drawLine(10, i, 810, i);
        }
    }

    public void fillCell(int x, int y) {
        fillCells.add(new Point(x, y));
        repaint();
    }
}