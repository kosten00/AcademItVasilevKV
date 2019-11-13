package ru.academints.vasilev.minesweeper.view;

import ru.academints.vasilev.minesweeper.model.MinesweeperBoardModel;

import javax.swing.*;

public class MinesweeperGUI {
    private int boardSize;
    private int bombsCount;

    public MinesweeperGUI() {
        initVisibleBoard();
    }

    //temporary starts just board with cells
    public void initVisibleBoard() {
        SwingUtilities.invokeLater(() -> {
            new MinesweeperVisibleBoard();
        });
    }

    public void initMainFrame() {
        JFrame mainFrame = new JFrame("Minesweeper");
        mainFrame.setSize(500, 500);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }
}