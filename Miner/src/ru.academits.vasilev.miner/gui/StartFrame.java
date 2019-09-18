package ru.academits.vasilev.miner.gui;

import javax.swing.*;
import java.awt.*;

public class StartFrame extends JFrame {
    private JButton newGame = new JButton("New game");
    private JButton highScore = new JButton("High score");
    private JButton about = new JButton("About");
    private JButton exit = new JButton("Exit");
    //TODO buttons width, add stream

    public StartFrame() {
        super("Miner");
        this.setBounds(0, 0, 250, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(4, 0, 100, 0));

        container.add(newGame);
        container.add(highScore);
        container.add(about);
        container.add(exit);
    }
}