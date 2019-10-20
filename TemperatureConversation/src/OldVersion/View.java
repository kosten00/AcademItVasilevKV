package OldVersion;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class View extends JFrame {
    private final static int WIDTH = 450;
    private final static int HEIGHT = 250;

    private JButton convertButton;
    private ButtonGroup radioGroupFrom;
    private ButtonGroup radioGroupTo;
    private JTextField inputTemperatureField;
    private JLabel outputTemperatureField;

    public JButton getConvertButton() {
        return convertButton;
    }

    public ButtonGroup getRadioGroupFrom() {
        return radioGroupFrom;
    }

    public ButtonGroup getRadioGroupTo() {
        return radioGroupTo;
    }

    public JTextField getInputTemperatureField() {
        return inputTemperatureField;
    }

    public JLabel getOutputTemperatureField() {
        return outputTemperatureField;
    }

    public View() {
        super("Temperature conversation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.ipadx = 10;

        JLabel label1 = new JLabel("Select input scale:");
        c.gridx = 0;
        c.gridy = 0;
        add(label1, c);

        JLabel label2 = new JLabel("Select output scale:");
        c.gridx = 2;
        c.gridy = 0;
        add(label2, c);

        JLabel label3 = new JLabel("Input temperature:");
        c.gridx = 0;
        c.gridy = 4;
        add(label3, c);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        inputTemperatureField = new JTextField(5);
        c.gridx = 0;
        c.gridy = 5;
        inputTemperatureField.setToolTipText("Input field");
        inputTemperatureField.setBorder(border);
        add(inputTemperatureField, c);

        outputTemperatureField = new JLabel();
        c.gridx = 2;
        c.gridy = 5;
        c.ipadx = 2;
        outputTemperatureField.setToolTipText("Output field");
        outputTemperatureField.setBackground(Color.white);
        outputTemperatureField.setOpaque(true);
        outputTemperatureField.setBorder(border);
        add(outputTemperatureField, c);

        JRadioButton celsiusButtonFrom = new JRadioButton("Celsius", true);
        c.gridx = 0;
        c.gridy = 1;
        celsiusButtonFrom.setActionCommand("celsius");
        add(celsiusButtonFrom, c);

        JRadioButton fahrenheitButtonFrom = new JRadioButton("Fahrenheit", false);
        c.gridx = 0;
        c.gridy = 2;
        fahrenheitButtonFrom.setActionCommand("fahrenheit");
        add(fahrenheitButtonFrom, c);

        JRadioButton kelvinButtonFrom = new JRadioButton("Kelvin", false);
        c.gridx = 0;
        c.gridy = 3;
        kelvinButtonFrom.setActionCommand("kelvin");
        add(kelvinButtonFrom, c);

        radioGroupFrom = new ButtonGroup();
        radioGroupFrom.add(celsiusButtonFrom);
        radioGroupFrom.add(fahrenheitButtonFrom);
        radioGroupFrom.add(kelvinButtonFrom);

        convertButton = new JButton("Convert");
        c.gridx = 1;
        c.gridy = 4;
        add(convertButton, c);

        JRadioButton celsiusButtonTo = new JRadioButton("Celsius", true);
        c.gridx = 2;
        c.gridy = 1;
        celsiusButtonTo.setActionCommand("celsius");
        add(celsiusButtonTo, c);

        JRadioButton fahrenheitButtonTo = new JRadioButton("Fahrenheit", false);
        c.gridx = 2;
        c.gridy = 2;
        fahrenheitButtonTo.setActionCommand("fahrenheit");
        add(fahrenheitButtonTo, c);

        JRadioButton kelvinButtonTo = new JRadioButton("Kelvin", false);
        c.gridx = 2;
        c.gridy = 3;
        kelvinButtonTo.setActionCommand("kelvin");
        add(kelvinButtonTo, c);

        radioGroupTo = new ButtonGroup();
        radioGroupTo.add(celsiusButtonTo);
        radioGroupTo.add(fahrenheitButtonTo);
        radioGroupTo.add(kelvinButtonTo);
    }
}