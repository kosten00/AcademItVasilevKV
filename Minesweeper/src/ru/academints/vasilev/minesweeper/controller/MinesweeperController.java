package ru.academints.vasilev.minesweeper.controller;

import ru.academints.vasilev.minesweeper.view.GeneralWindow;
import ru.academints.vasilev.minesweeper.view.MinesweeperGUI;

public class MinesweeperController {
    private static final int DEFAULT_BOMBS_COUNT = 9;
    private static final int DEFAULT_GAMING_BOARD_SIZE = 10;

    public MinesweeperController() {
        new GeneralWindow("Minesweeper", 600);
        //new MinesweeperGUI(DEFAULT_BOMBS_COUNT, DEFAULT_GAMING_BOARD_SIZE);
    }
}