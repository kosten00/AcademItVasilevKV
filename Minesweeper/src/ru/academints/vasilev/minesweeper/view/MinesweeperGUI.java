package ru.academints.vasilev.minesweeper.view;

import ru.academints.vasilev.minesweeper.model.MinesweeperBoardModel;

import javax.swing.*;

public class MinesweeperGUI {
    private int boardSize;
    private int bombsCount;

    int frameWidth = 555;
    int frameHeight = 570;

    public MinesweeperGUI() {
        initVisibleBoard();
    }

    public void initVisibleBoard() {
        SwingUtilities.invokeLater(() -> {
            initMainFrame();
        });
    }

    public void initMainFrame() {
        JFrame mainFrame = new JFrame("Minesweeper");
        mainFrame.setSize(frameWidth, frameHeight);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

        mainFrame.add(new MinesweeperVisibleBoard(frameWidth, frameHeight));
    }
}