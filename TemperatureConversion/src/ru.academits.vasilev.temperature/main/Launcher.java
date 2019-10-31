package ru.academits.vasilev.temperature.main;

import ru.academits.vasilev.temperature.scales.Scale;
import ru.academits.vasilev.temperature.*;
import ru.academits.vasilev.temperature.scales.Celsius;
import ru.academits.vasilev.temperature.scales.Fahrenheit;
import ru.academits.vasilev.temperature.scales.Kelvin;

public class Launcher {
    public static void main(String[] args) {
        new Controller(new Scale[]{new Celsius("celsius"),
                new Fahrenheit("fahrenheit"),
                new Kelvin("kelvin")});
    }
}