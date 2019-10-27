package ru.academits.vasilev.temperature.scales;

public class Fahrenheit extends Scale {
    public Fahrenheit(String name) {
        super(name);
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return (temperature * ((double) 9 / 5)) + 32;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return (temperature - 32) * ((double) 5 / 9);
    }
}