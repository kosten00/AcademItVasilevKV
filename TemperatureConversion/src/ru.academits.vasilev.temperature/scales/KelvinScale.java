package ru.academits.vasilev.temperature.scales;

public class KelvinScale extends Scale {
    public KelvinScale(String name) {
        super(name);
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature + 273.15;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature - 273.15;
    }
}
