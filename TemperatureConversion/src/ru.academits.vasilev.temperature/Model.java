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
        List<Scale> scalesForConversion = Arrays.stream(scales).
                filter(scale -> scale.toString().equals(from) || scale.toString().equals(to)).
                collect(Collectors.toList());

        return scalesForConversion.get(1).convertFromCelsius(scalesForConversion.get(0).convertToCelsius(temperature));
    }
}