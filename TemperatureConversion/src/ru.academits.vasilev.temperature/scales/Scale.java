package ru.academits.vasilev.temperature.scales;

public abstract class Scale {
    private String name;

    public Scale(String name) {
        this.name = name;
    }

    public abstract double convertFromCelsius(double temperature);

    public abstract double convertToCelsius(double temperature);

    @Override
    public String toString() {
        return name;
    }
}