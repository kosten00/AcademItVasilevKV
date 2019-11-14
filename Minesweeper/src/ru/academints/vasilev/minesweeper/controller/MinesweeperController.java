package ru.academints.vasilev.minesweeper.controller;

import ru.academints.vasilev.minesweeper.view.MinesweeperGUI;

public class MinesweeperController {
    private String name = "Minesweeper";

    public MinesweeperController() {
        new MinesweeperGUI(555, 570);
    }
}