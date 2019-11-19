package ru.academints.vasilev.minesweeper.view.GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private String title;
    private JFrame frame;
    private GridBagConstraints constraints;

    private JButton[] buttons;
    private static final String[] BUTTONS_TEXT = {"New Game", "Exit", "High scores", "About"};

    private JTextField[] textFields;

    private static final String[] LABELS_TEXT = {"Board size", "Bombs count"};
    private JLabel[] textFieldsLabels;

    public MainFrame(String title) {
        this.title = title;

        SwingUtilities.invokeLater(this::initFrame);
    }

    private void initFrame() {
        frame = new JFrame(title);
        frame.setSize(500, 350);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null);
        constraints = new GridBagConstraints();

        initComponents();

        frame.setVisible(true);
    }

    private void initComponents() {
        buttons = new JButton[BUTTONS_TEXT.length];
        switchLayoutRow();
        addButtons();

        textFieldsLabels = new JLabel[LABELS_TEXT.length];
        switchLayoutRow();
        addLabels();

        textFields = new JTextField[textFieldsLabels.length];
        switchLayoutRow();
        addTextFields();
    }

    private void addButtons() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(BUTTONS_TEXT[i]);

            frame.add(buttons[i]);

            addButtonListener(buttons[i]);
        }
    }

    private void addLabels() {
        for (int i = 0; i < textFieldsLabels.length; i++) {
            textFieldsLabels[i] = new JLabel(LABELS_TEXT[i]);

            frame.add(textFieldsLabels[i], constraints);
        }
    }

    private void addTextFields() {
        for (int i = 0; i < textFields.length; i++) {
            textFields[i] = new JTextField(5);
            frame.add(textFields[i], constraints);
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
            }
        });
    }

    private void switchLayoutRow() {
        constraints.gridy++;
        constraints.gridwidth = 2;
    }
}

class Main{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame("name"));
    }
}