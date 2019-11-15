package ru.academints.vasilev.minesweeper.view;

import ru.academints.vasilev.minesweeper.model.MinesweeperModel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class MinesweeperGUI {
    private MinesweeperModel minesweeperModel;

    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    private int width = ((int) SCREEN_SIZE.getWidth()) / 4;
    private int height = ((int) SCREEN_SIZE.getHeight()) / 4;

    private int boardSize;
    private int bombsCount;

    private JTextField inputForBombsCount;
    private JTextField inputForBoardSize;

    private JFrame boardFrame;

    private JFrame mainFrame;

    private JButton startGame;
    private JButton exit;
    private JButton highScores;
    private JButton about;

    private JButton[][] gamingBoardButtons;

    public MinesweeperGUI(int bombsCount, int boardSize) {
        this.bombsCount = bombsCount;
        this.boardSize = boardSize;

        initFrames();
    }

    public void initFrames() {
        SwingUtilities.invokeLater(() -> {
            initMainFrame();

            initMainFrameListeners();
        });
    }

    public void initMainFrame() {
        mainFrame = new JFrame("Minesweeper");
        mainFrame.setSize(width, height);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(true);
        mainFrame.setVisible(true);

        mainFrame.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        startGame = new JButton("New Game");
        mainFrame.add(startGame);

        exit = new JButton("Exit");
        mainFrame.add(exit);

        highScores = new JButton("High scores");
        mainFrame.add(highScores);

        about = new JButton("About");
        mainFrame.add(about);

        inputForBoardSize = new JTextField(String.valueOf(boardSize), 5);
        inputForBoardSize.setToolTipText("Board size");
        c.gridy = 1;
        mainFrame.add(inputForBoardSize, c);

        inputForBombsCount = new JTextField(String.valueOf(bombsCount), 5);
        inputForBombsCount.setToolTipText("Bombs count");
        mainFrame.add(inputForBombsCount, c);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initGamingBoard() {
        minesweeperModel = new MinesweeperModel(boardSize, bombsCount);

        int gamingBoardSize = Integer.parseInt(inputForBoardSize.getText());

        boardFrame = new JFrame("Gaming board");
        boardFrame.setSize(width * 2, width * 2);
        boardFrame.setLocationRelativeTo(null);

        boardFrame.setLayout(new GridLayout(gamingBoardSize, gamingBoardSize));

        gamingBoardButtons = new JButton[gamingBoardSize][gamingBoardSize];

        for (int i = 0; i < gamingBoardSize; i++) {
            for (int j = 0; j < gamingBoardSize; j++) {
                gamingBoardButtons[i][j] = new JButton();

                boardFrame.add(gamingBoardButtons[i][j]);

                initGamingBoardButtonListener(gamingBoardButtons[i][j], i, j);
            }
        }

        boardFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initMainFrameListeners() {
        startGame.addActionListener(e -> {
            initGamingBoard();

            boardFrame.setVisible(true);
            mainFrame.setVisible(false);
        });

        exit.addActionListener(e -> System.exit(0));
    }

    private void initGamingBoardButtonListener(JButton button, int i, int j) {
        button.addActionListener(e -> {
            if (minesweeperModel.cells[i][j].isMined()) {
                minesweeperModel.cells[i][j].open();

                button.setText("Bomb");
            } else {
                minesweeperModel.cells[i][j].open();

                button.setText(String.valueOf(minesweeperModel.cells[i][j].getBombsNearbyCount()));
            }
        });
    }

    private void scanGamingBoard() {

    }
}