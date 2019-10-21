package ru.academits.vasilev.temperature.main;

import ru.academits.vasilev.temperature.Scale;
import ru.academits.vasilev.temperature.*;

public class Launcher {
    public static void main(String[] args) {
        Scale celsius = new Scale("Celsius") {
            @Override
            public double convertFromCelsius(double temperature) {
                return temperature;
            }

            @Override
            public double convertToCelsius(double temperature) {
                return temperature;
            }
        };

        Scale fahrenheit = new Scale("Fahrenheit") {
            @Override
            public double convertFromCelsius(double temperature) {
                return (temperature * (9. / 5)) + 32;
            }

            @Override
            public double convertToCelsius(double temperature) {
                return (temperature - 32) * (5. / 9);
            }
        };

        Scale kelvin = new Scale("Kelvin") {
            @Override
            public double convertFromCelsius(double temperature) {
                return temperature + 273.15;
            }

            @Override
            public double convertToCelsius(double temperature) {
                return temperature - 273.15;
            }
        };

        Scale[] scales = new Scale[]{celsius, fahrenheit, kelvin};

        new Controller(scales);
    }
}