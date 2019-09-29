package ru.academints.vasilev.minesweeper.gui;

import javax.swing.*;
import java.awt.*;

//GridBagLayout
public class MainFrame extends JFrame {
    private VisualBoard visualBoard;
    private Toolbar toolbar;
    private int width;
    private int height;

    public MainFrame(int width, int height) {
        this.width = width;
        this.height = height;

        JFrame mainFrame = new JFrame("Minesweeper");
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setSize(this.width, this.height);
        mainFrame.setLocationRelativeTo(null); //оконо по центру
//
//        GridBagConstraints c = new GridBagConstraints();
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.ipady = 0;
//        c.weighty = 1.0;
//        c.anchor = GridBagConstraints.PAGE_END;
//        c.insets = new Insets(10,0,0,0);
//        c.gridx = 1;
//        c. gridwidth = 2;
//        c.gridy = 2;
        mainFrame.add(toolbar);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame(600, 600);
            }
        });

    }
}
