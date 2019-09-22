package ru.academits.vasilev.miner.main;

import ru.academits.vasilev.miner.Board;
import ru.academits.vasilev.miner.gui.StartFrame;

public class MinerMain {
    public static void main(String[] args) {
        //javax.swing.SwingUtilities.invokeLater(StartFrame::new);

        Board board = new Board(9, 9);

        System.out.println(board);
    }
}