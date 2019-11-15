package ru.academints.vasilev.minesweeper.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralWindow {
    private String title;
    private JFrame frame;
    private int width;
    private int height;
    private GridBagConstraints constraints;

    private final int WIDTH_TO_HEIGHT_RATIO = 2;
    private final int DEFAULT_COMPONENTS_PER_ROW = 4;

    private JButton[] buttons;
    private static final String[] BUTTONS_TEXT = {"New Game", "Exit", "High scores", "About"};

    private JTextField[] textFields;

    private static final String[] LABELS_TEXT = {"Board size", "Bombs count"};
    private JLabel[] textFieldsLabels;

    public GeneralWindow(String title, int size) {
        this.title = title;
        width = size;
        height = size / WIDTH_TO_HEIGHT_RATIO;

        SwingUtilities.invokeLater(this::initFrame);
    }

    private void initFrame() {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null);
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        initComponents();
    }

    private void initComponents() {
        buttons = new JButton[BUTTONS_TEXT.length];
        switchLayoutRow(DEFAULT_COMPONENTS_PER_ROW / BUTTONS_TEXT.length);
        addButtons();

        textFieldsLabels = new JLabel[LABELS_TEXT.length];
        switchLayoutRow(DEFAULT_COMPONENTS_PER_ROW / textFieldsLabels.length);
        addLabels();

        textFields = new JTextField[textFieldsLabels.length];
        switchLayoutRow(DEFAULT_COMPONENTS_PER_ROW / textFields.length);
        addTextFields();
    }

    private void addButtons() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(BUTTONS_TEXT[i]);

            frame.add(buttons[i]);

            addButtonListeners(buttons[i]);
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

    private void addButtonListeners(JButton button) {
        button.addActionListener(e -> {
            switch (button.getText()) {
                case "New Game":
                    System.out.println("new game pressed");
                    break;
                case "Exit":
                    System.out.println("exit pressed");
                    break;
            }
        });
    }

    private void switchLayoutRow(int componentWidth) {
        constraints.gridy++;
        constraints.gridwidth = componentWidth;
    }
}
