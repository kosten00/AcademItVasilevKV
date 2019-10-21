package ru.academits.vasilev.temperature;

public class Controller {
    public Controller(Scale[] scales) {
        new View(new Model(scales));
    }
}