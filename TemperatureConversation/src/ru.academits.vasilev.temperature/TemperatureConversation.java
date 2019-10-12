package ru.academits.vasilev.temperature;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
 * Научитесь:
 * Начальное знакомство с UI
 * MVC и отделение логики от представления
 * Принцип открытости-закрытости
 */

public class TemperatureConversation extends JFrame {
    private final static int WIDTH = 400;
    private final static int HEIGHT = 250;

    private JButton convertButton;
    private ButtonGroup bg1;
    private ButtonGroup bg2;
    private JTextField textField;
    JLabel resultLabel;

    String result;

    private ActionListener aL = new Listener();

    public TemperatureConversation() {
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

        resultLabel = new JLabel();
        c.gridx = 2;
        c.gridy = 5;
        add(resultLabel, c);

        JRadioButton checkBox1 = new JRadioButton("Celsius", false);
        c.gridx = 0;
        c.gridy = 1;
        add(checkBox1, c);

        JRadioButton checkBox2 = new JRadioButton("Fahrenheit", false);
        c.gridx = 0;
        c.gridy = 2;
        add(checkBox2, c);

        JRadioButton checkBox3 = new JRadioButton("Kelvin", false);
        c.gridx = 0;
        c.gridy = 3;
        add(checkBox3, c);

        bg1 = new ButtonGroup();
        bg1.add(checkBox1);
        bg1.add(checkBox2);
        bg1.add(checkBox3);

        convertButton = new JButton("Convert");
        c.gridx = 1;
        c.gridy = 4;
        add(convertButton, c);
        convertButton.addActionListener(aL);

        JRadioButton checkBox4 = new JRadioButton("Celsius", false);
        c.gridx = 2;
        c.gridy = 1;
        add(checkBox4, c);

        JRadioButton checkBox5 = new JRadioButton("Fahrenheit", false);
        c.gridx = 2;
        c.gridy = 2;
        add(checkBox5, c);

        JRadioButton checkBox6 = new JRadioButton("Kelvin", false);
        c.gridx = 2;
        c.gridy = 3;
        add(checkBox6, c);

        bg2 = new ButtonGroup();
        bg2.add(checkBox4);
        bg2.add(checkBox5);
        bg2.add(checkBox6);

        textField = new JTextField(5);
        c.gridx = 0;
        c.gridy = 5;
        add(textField, c);
    }

    class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            resultLabel.setText("Hello!");
        }
    }
}
