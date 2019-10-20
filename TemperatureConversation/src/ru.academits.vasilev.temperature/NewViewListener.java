package ru.academits.vasilev.temperature;

public class NewViewListener {
    private NewView view;

    public NewViewListener(NewView view) {
        this.view = view;
    }

    public String getFrom() {
        return view.getFrom();
    }

    public String getTo() {
        return view.getTo();
    }

    public double getTemperature() {
        return view.getTemperature();
    }

}
