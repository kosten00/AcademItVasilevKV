package ru.academits.vasilev.temperature.main;

import ru.academits.vasilev.temperature.scales.Scale;
import ru.academits.vasilev.temperature.*;
import ru.academits.vasilev.temperature.scales.CelsiusScale;
import ru.academits.vasilev.temperature.scales.FahrenheitScale;
import ru.academits.vasilev.temperature.scales.KelvinScale;

public class Launcher {
    public static void main(String[] args) {
        new Controller(new Scale[]{new CelsiusScale("celsius"),
                new FahrenheitScale("fahrenheit"),
                new KelvinScale("kelvin")});
    }
}