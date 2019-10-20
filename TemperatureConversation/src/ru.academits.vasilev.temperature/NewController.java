package ru.academits.vasilev.temperature;

//controller starts view
//controller generates model

import Scales.Scale;

public class NewController {
    private Scale[] scales;

    public NewController(Scale[] scales) {
        this.scales = scales;
    }

    public Scale[] getScales() {
        return scales;
    }

    public void receiveDirections(String from, String to, double temperature) {
    }

    public void sendToModel() {
    }
}