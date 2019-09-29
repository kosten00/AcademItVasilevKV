package ru.academints.vasilev.minesweeper.main;

import ru.academints.vasilev.minesweeper.gui.VisualBoard;

public class MinerMain {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new VisualBoard(9, 5));

        //Board board = new Board(9, 5);

        //System.out.println(board);
    }
}