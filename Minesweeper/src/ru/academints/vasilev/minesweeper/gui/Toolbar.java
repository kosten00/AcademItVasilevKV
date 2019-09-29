package ru.academints.vasilev.minesweeper.gui;

//Exit, About, New Game, High Scores

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JPanel {
    private JButton newGameButton = new JButton();
    private JButton highScoresButton = new JButton();
    private JButton aboutButton = new JButton();
    private JButton exitButton = new JButton();


    public Toolbar() {
        JPanel panel = new JPanel();
        panel.setSize(50, 600);
        panel.setVisible(true);

        panel.setLayout(new FlowLayout());

        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        panel.add(newGameButton);
        panel.add(highScoresButton);
        panel.add(aboutButton);
        panel.add(exitButton);
    }


}
