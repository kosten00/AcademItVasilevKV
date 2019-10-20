package ru.academits.vasilev.temperature;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class NewView {
    NewModel model;

    private final static int WIDTH = 450;
    private final static int HEIGHT = 250;
    private final static String NAME = "Temperatures Conversation";

    private JButton convertButton;
    private JTextField inputTemperatureField;
    private JLabel outputTemperatureField;
    private JComboBox fromScales;
    private JComboBox toScales;

    public NewView(NewModel model) {
        this.model = model;
        SwingUtilities.invokeLater(this::initMainFrame);
    }

    private void initMainFrame() {
        JFrame mainFrame = new JFrame(NAME);
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

        mainFrame.setLayout(new GridBagLayout());

        Insets insets = new Insets(7, 7, 7, 7);

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.ipadx = 10;
        c.insets = insets;

        JLabel label1 = new JLabel("Select input scale:");
        c.gridx = 0;
        c.gridy = 0;

        mainFrame.add(label1, c);

        fromScales = new JComboBox(model.getScaleNames());
        c.gridx = 0;
        c.gridy = 1;
        mainFrame.add(fromScales, c);

        JLabel label2 = new JLabel("Select output scale:");
        c.gridx = 2;
        c.gridy = 0;
        mainFrame.add(label2, c);

        toScales = new JComboBox(model.getScaleNames());
        c.gridx = 2;
        c.gridy = 1;
        mainFrame.add(toScales, c);

        JLabel label3 = new JLabel("Input temperature:");
        c.gridx = 0;
        c.gridy = 4;
        mainFrame.add(label3, c);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        inputTemperatureField = new JTextField(5);
        c.gridx = 0;
        c.gridy = 5;
        inputTemperatureField.setToolTipText("Input field");
        inputTemperatureField.setBorder(border);
        mainFrame.add(inputTemperatureField, c);

        outputTemperatureField = new JLabel();
        c.gridx = 2;
        c.gridy = 5;
        c.ipadx = 2;
        outputTemperatureField.setToolTipText("Output field");
        outputTemperatureField.setBackground(Color.white);
        outputTemperatureField.setOpaque(true);
        outputTemperatureField.setBorder(border);
        mainFrame.add(outputTemperatureField, c);

        convertButton = new JButton("Convert");
        c.gridx = 1;
        c.gridy = 4;
        mainFrame.add(convertButton, c);

        initListeners();
    }

    private void initListeners() {
        convertButton.addActionListener(e -> inputTemperatureField.getText());
    }
}
