package ru.academints.vasilev.minesweeper.main;

import ru.academints.vasilev.minesweeper.Board;

public class MinerMain {
    public static void main(String[] args) {
        //javax.swing.SwingUtilities.invokeLater(StartFrame::new);

        Board board = new Board(3, 1);

        System.out.println(board);
    }
}