package ru.academits.vasilev.temperature;

import Scales.Scale;

public class Controller {
    public Controller(Scale[] scales) {
        new View(new Model(scales));
    }
}