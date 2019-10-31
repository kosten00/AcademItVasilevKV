package ru.academits.vasilev.temperature.scales;

public class Celsius extends Scale {
    public Celsius(String name) {
        super(name);
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }
}