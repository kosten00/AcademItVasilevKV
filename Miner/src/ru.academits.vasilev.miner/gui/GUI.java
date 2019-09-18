package ru.academits.vasilev.miner.gui;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private final int FRAME_SIZE = Toolkit.getDefaultToolkit().getScreenSize().width / 4;
    private final int DEFAULT_CELLS_ON_FIELD = 9;

    public void openStartFrame() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Mine Sweeper");
            frame.setSize(FRAME_SIZE, FRAME_SIZE);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);

            JButton button;

            button = new JButton("Start game");
            button = new JButton("Options");

            GridBagConstraints c = new GridBagConstraints();


        });
    }


}