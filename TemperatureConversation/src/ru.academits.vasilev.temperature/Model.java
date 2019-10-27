package ru.academits.vasilev.temperature;


import ru.academits.vasilev.temperature.scales.Scale;

import java.util.*;
import java.util.stream.Collectors;

public class Model {
    private Scale[] scales;

    public Model(Scale[] scales) {
        this.scales = scales;
    }

    public String[] getScaleNames() {
        int scalesCount = scales.length;

        String[] names = new String[scalesCount];

        int i = 0;
        for (Scale scale : scales) {
            names[i] = scale.getName();

            i++;
        }

        return names;
    }

    public double convert(String from, String to, double temperature) {
        if (from.equals(to)) {
            return temperature;
        }

        List<Scale> list = Arrays.stream(scales).
                filter(scale -> scale.getName().equals(from) || scale.getName().equals(to)).
                collect(Collectors.toList());

        return list.get(1).convertFromCelsius(list.get(0).convertToCelsius(temperature));
    }
}