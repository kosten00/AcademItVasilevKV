package ru.academits.vasilev.temperature;


import ru.academits.vasilev.temperature.scales.Scale;

import java.util.*;
import java.util.stream.Collectors;

public class Model {
    private Scale[] scales;

    public Model(Scale[] scales) {
        this.scales = scales;
    }

    public Scale[] getScales() {
        return scales;
    }

    public double convert(Scale from, Scale to, double temperature) {
        if (from.equals(to)) {
            return temperature;
        }

        return from.convertFromCelsius(to.convertToCelsius(temperature));
    }
}