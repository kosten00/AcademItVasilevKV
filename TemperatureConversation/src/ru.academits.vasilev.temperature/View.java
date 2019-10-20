package ru.academits.vasilev.temperature;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class View {
    private Model model;

    private final static int WIDTH = 450;
    private final static int HEIGHT = 250;
    private final static String NAME = "Temperatures Conversation";

    private JButton convertButton;
    private JTextField inputTemperatureField;
    private JLabel outputTemperatureField;
    private JComboBox fromScales;
    private JComboBox toScales;

    private String from = "";
    private String to = "";
    private String temperature = "";

    public View(Model model) {
        this.model = model;
        SwingUtilities.invokeLater(() -> {
            initMainFrame();
            initListeners();
        });
    }

    //public String getFrom() {
    //return from;
    // }

    // public String getTo() {
    // return to;
    // }

    public String getTemperature() {
       return temperature.replaceAll(",", ".");
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
    }

    private void initListeners() {
        convertButton.addActionListener(e -> {
            from = (String) fromScales.getSelectedItem();
            to = (String) toScales.getSelectedItem();
            temperature = inputTemperatureField.getText();

            if (hasErrors()) {
                return;
            }

            double temperatureToDouble = Double.parseDouble(getTemperature());

            String result = Double.toString(model.convert(from, to, temperatureToDouble));

            outputTemperatureField.setText(result);
        });
    }

    private boolean hasErrors() {
        if (temperature.equals("")) {
            return true;
        }

        int commaSignIndex = temperature.indexOf(",");

        if (temperature.lastIndexOf(",") != commaSignIndex) {
            outputTemperatureField.setText("Only one \",\" allowed");
            return true;
        }

        int dotSignIndex = temperature.indexOf(".");

        if (temperature.lastIndexOf(".") != dotSignIndex) {
            outputTemperatureField.setText("Only one \".\" allowed");
            return true;
        }

        int minusSignIndex = temperature.indexOf("-");

        if (temperature.lastIndexOf("-") != minusSignIndex) {
            outputTemperatureField.setText("Only one \"-\" allowed");
            return true;
        }

        for (int i = 0; i < temperature.length(); i++) {
            if (i == commaSignIndex || i == minusSignIndex || i == dotSignIndex) {
                continue;
            }

            if (!Character.isDigit(temperature.charAt(i))) {
                outputTemperatureField.setText("Only digits allowed");
                return true;
            }
        }

        return false;
    }
}