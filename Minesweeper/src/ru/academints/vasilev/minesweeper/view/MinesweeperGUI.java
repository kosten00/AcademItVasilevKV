package ru.academints.vasilev.minesweeper.view;

import javax.swing.*;
import java.awt.*;

//greet frame with buttons, hides after start game button pressed. vise-a-versa

public class MinesweeperGUI {
    private int frameWidth;
    private int frameHeight;

    //private int boardSize;
    //private int bombsCount;

    private JTextField bombsCount;
    private JTextField boardSize;

    private JFrame boardFrame;

    private JFrame mainFrame;

    private JButton startNewGame;
    private JButton exit;
    private JButton highScores;
    private JButton about;

    public MinesweeperGUI(int frameWidth, int frameHeight) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

        initFrames();
    }

    public void initFrames() {
        SwingUtilities.invokeLater(() -> {
            initMainFrame();

            initListeners();
        });
    }

    public void initMainFrame() {
        mainFrame = new JFrame("Minesweeper");
        mainFrame.setSize(frameWidth, frameHeight);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(true);
        mainFrame.setVisible(true);

        mainFrame.setLayout(new GridBagLayout());

        startNewGame = new JButton("New Game");
        mainFrame.add(startNewGame);

        exit = new JButton("Exit");
        mainFrame.add(exit);

        highScores = new JButton("High scores");
        mainFrame.add(highScores);

        about = new JButton("About");
        mainFrame.add(about);

        //add grid constraints to look nice
        boardSize = new JTextField("10");
        mainFrame.add(boardSize);

        bombsCount = new JTextField("9");
        mainFrame.add(bombsCount);
    }

    private void initGamingBoard() {
        boardFrame = new JFrame("Gaming board");
        boardFrame.setSize(500, 500);

        boardFrame.setLayout(new GridLayout(10, 10));

        for (int i = 0; i < Integer.parseInt(boardSize.getText()); i++) {
            for (int j = 0; j < Integer.parseInt(boardSize.getText()); j++) {
                boardFrame.add(new JButton());
            }
        }
    }

    private void initListeners() {
        startNewGame.addActionListener(e -> {
            //looks like it still runs from invoke later stream
            initGamingBoard();

            boardFrame.setVisible(true);
            mainFrame.setVisible(false);
        });
    }
}