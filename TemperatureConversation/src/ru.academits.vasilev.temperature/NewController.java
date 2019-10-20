package ru.academits.vasilev.temperature;

//controller starts view
//controller generates model

import Scales.Scale;

public class NewController {
    private Scale[] scales;
    private NewModel model;
    private NewView view;

    public NewController(Scale[] scales) {
        this.scales = scales;
        this.model = new NewModel(this.scales);
        this.view = new NewView(this.model);

        view.initListeners();
    }
}