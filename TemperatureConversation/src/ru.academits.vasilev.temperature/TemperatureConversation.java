package ru.academits.vasilev.temperature;

import javax.swing.*;

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
    final static int WIDTH = 200;
    final static int HEIGHT = 300;

    public TemperatureConversation() {
        super("Temperature conversation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
    }
}
