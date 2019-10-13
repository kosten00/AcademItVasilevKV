package ru.academits.vasilev.temperature.main;

import ru.academits.vasilev.temperature.Controller;
import ru.academits.vasilev.temperature.Model;
import ru.academits.vasilev.temperature.View;

import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Controller(new View(), new Model());
        });
    }
}