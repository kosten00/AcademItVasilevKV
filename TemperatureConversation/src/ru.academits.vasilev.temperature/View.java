package ru.academits.vasilev.temperature;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

/**
 * Задача 7. Сделать программу на Swing  / Windows Forms для перевода температуры из одной шкалы в другую.
 * Лекции, нужные для решения задачи: 1-15, 21-22.
 * Необходимая функциональность:
 * 1. Ввод температуры в поле ввода
 * 2. Должна быть кнопка, которая переводит температуру из одной шкалы в другую
 * 3. Результат перевода должен выводиться на форму, при этом быть не редактируемым
 * 4. Можно задать из какой шкалы и в какую переводить
 * 5. Доступные шкалы: цельсия, фаренгейта, кельвина
 * 6. Если ввели не число, то нужно вывести ошибку
 * 7. Обязательно использовать layout manager’ы
 * Научитесь: Начальное знакомство с UI
 * MVC и отделение логики от представления
 * Принцип открытости-закрытости
 */

public class View extends JFrame {
    private final static int WIDTH = 400;
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

        inputTemperatureField = new JTextField(5);
        c.gridx = 0;
        c.gridy = 5;
        inputTemperatureField.setToolTipText("Input field");
        add(inputTemperatureField, c);

        outputTemperatureField = new JLabel();
        c.gridx = 2;
        c.gridy = 5;
        outputTemperatureField.setToolTipText("Output field");
        add(outputTemperatureField, c);

        JRadioButton celsiusButtonFrom = new JRadioButton("Celsius", true);
        c.gridx = 0;
        c.gridy = 1;
        celsiusButtonFrom.setActionCommand("celsiusButtonFrom");
        add(celsiusButtonFrom, c);

        JRadioButton fahrenheitButtonFrom = new JRadioButton("Fahrenheit", false);
        c.gridx = 0;
        c.gridy = 2;
        fahrenheitButtonFrom.setActionCommand("fahrenheitButtonFrom");
        add(fahrenheitButtonFrom, c);

        JRadioButton kelvinButtonFrom = new JRadioButton("Kelvin", false);
        c.gridx = 0;
        c.gridy = 3;
        kelvinButtonFrom.setActionCommand("kelvinButtonFrom");
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
        celsiusButtonTo.setActionCommand("celsiusButtonTo");
        add(celsiusButtonTo, c);

        JRadioButton fahrenheitButtonTo = new JRadioButton("Fahrenheit", false);
        c.gridx = 2;
        c.gridy = 2;
        fahrenheitButtonTo.setActionCommand("fahrenheitButtonTo");
        add(fahrenheitButtonTo, c);

        JRadioButton kelvinButtonTo = new JRadioButton("Kelvin", false);
        c.gridx = 2;
        c.gridy = 3;
        kelvinButtonTo.setActionCommand("kelvinButtonTo");
        add(kelvinButtonTo, c);

        radioGroupTo = new ButtonGroup();
        radioGroupTo.add(celsiusButtonTo);
        radioGroupTo.add(fahrenheitButtonTo);
        radioGroupTo.add(kelvinButtonTo);
    }
}