package ru.academits.vasilev.temperature.scales;

public class CelsiusScale extends Scale {
    public CelsiusScale(String name) {
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