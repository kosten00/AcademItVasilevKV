package ru.academints.vasilev.minesweeper.gui.test;

import javax.swing.*;
import java.awt.*;

public class TestMainFrame extends JFrame {
    private JPanel mainPanel;

    public TestMainFrame() {
        super ("minesweeper");

        setLayout(new GridBagLayout());
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

}
