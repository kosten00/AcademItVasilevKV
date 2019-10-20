package ru.academits.vasilev.temperature;

import Scales.Scale;

public class Controller {
    public Controller(Scale[] scales) {
        Model model = new Model(scales);
        new View(model);
    }
}