package ru.academints.vasilev.minesweeper.gui;

import ru.academints.vasilev.minesweeper.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisibleBoard extends JPanel {
    Board board;

    public VisibleBoard(int boardSize, int minesCount) {
        Board board = new Board(boardSize, minesCount);

        setLayout(new GridLayout(boardSize, boardSize));
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                JButton button = new JButton();
                add(button);

            }
        }

    }
}

class ButtonHandler implements ActionListener {
    private Board board;

    public ButtonHandler() {
    }

    public void actionPerformed(ActionEvent event) {
    }
}
