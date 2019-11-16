package ru.academints.vasilev.minesweeper.view;

import ru.academints.vasilev.minesweeper.model.MinesweeperModel;

import javax.swing.*;
import java.awt.*;

public class GeneralWindow {
    private MinesweeperModel minesweeperModel;

    private int boardSize;
    private int bombsCount;

    private String title;
    private JFrame mainframe;
    private int width;
    private int height;
    private GridBagConstraints constraints;

    private JFrame gamingBoardFrame;
    private JButton[] gamingBoardButtons;

    private void initGamingBoard() {
        this.minesweeperModel = new MinesweeperModel(boardSize, bombsCount);

        gamingBoardFrame = new JFrame(title);
        gamingBoardFrame.setLocationRelativeTo(null);
        gamingBoardFrame.setLayout(new GridLayout(boardSize, boardSize));

        gamingBoardButtons = new JButton[boardSize];
    }

    private final int WIDTH_TO_HEIGHT_RATIO = 2;

    private JButton[] mainFrameButtons;
    private static final String[] BUTTONS_TEXT = {"New Game", "Exit", "High scores", "About"};

    private JTextField[] textFields;

    private static final String[] LABELS_TEXT = {"Board size", "Bombs count"};
    private JLabel[] textFieldsLabels;

    private static final String ABOUT_TEXT = "Created by\nVasilev K.V.";

    public GeneralWindow(String title, int size, int boardSize, int bombsCount) {
        this.title = title;
        width = size;
        height = size / WIDTH_TO_HEIGHT_RATIO;
        this.boardSize = boardSize;
        this.bombsCount = bombsCount;

        SwingUtilities.invokeLater(this::initMainFrame);
    }

    private void initMainFrame() {
        mainframe = new JFrame(title);
        mainframe.setSize(width, height);
        mainframe.setResizable(false);
        mainframe.setLayout(new GridBagLayout());
        mainframe.setLocationRelativeTo(null);
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(8, 8, 8, 8);
        initComponents();

        mainframe.setVisible(true);
        mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        mainFrameButtons = new JButton[BUTTONS_TEXT.length];
        switchLayoutRow(BUTTONS_TEXT.length);
        addButtons();

        textFieldsLabels = new JLabel[LABELS_TEXT.length];
        switchLayoutRow(textFieldsLabels.length);
        addLabels();

        textFields = new JTextField[textFieldsLabels.length];
        switchLayoutRow(textFields.length);
        addTextFields();
    }

//more easy to add flat 4 buttons
    private void addButtons() {
        for (int i = 0; i < mainFrameButtons.length; i++) {
            mainFrameButtons[i] = new JButton(BUTTONS_TEXT[i]);

            mainframe.add(mainFrameButtons[i]);

            addButtonListener(mainFrameButtons[i]);
        }
    }

    private void addLabels() {
        for (int i = 0; i < textFieldsLabels.length; i++) {
            textFieldsLabels[i] = new JLabel(LABELS_TEXT[i]);

            mainframe.add(textFieldsLabels[i], constraints);
        }
    }

    private void addTextFields() {
        for (int i = 0; i < textFields.length; i++) {
            textFields[i] = new JTextField(5);
            mainframe.add(textFields[i], constraints);
        }
    }

    private void addButtonListener(JButton button) {
        button.addActionListener(e -> {
            switch (button.getText()) {
                case "New Game":
                    System.out.println("new game pressed");
                    break;
                case "Exit":
                    System.exit(0);
                    break;
                case "About":
                    JOptionPane.showMessageDialog(mainframe, ABOUT_TEXT);
                    break;
                case "High scores":
                    System.out.println("Hi scores");
                    break;
            }
        });
    }

    private void switchLayoutRow(int componentWidth) {
        constraints.gridy++;
        constraints.gridwidth = componentWidth;
    }
}
