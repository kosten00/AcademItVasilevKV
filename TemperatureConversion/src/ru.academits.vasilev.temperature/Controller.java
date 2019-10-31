package ru.academits.vasilev.temperature;

import ru.academits.vasilev.temperature.scales.Scale;

public class Controller {
    public Controller(Scale[] scales) {
        String name = "Temperature conversion";

        new View(new Model(scales), name);
    }
}