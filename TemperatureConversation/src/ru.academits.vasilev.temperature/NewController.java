package ru.academits.vasilev.temperature;

import Scales.Scale;

public class NewController {
    private Scale[] scales;
    private NewModel model;

    public NewController(Scale[] scales) {
        this.scales = scales;
        this.model = new NewModel(this.scales);
        new NewView(this.model);
    }
}