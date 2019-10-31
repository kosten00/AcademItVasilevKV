package ru.academits.vasilev.temperature;

import ru.academits.vasilev.temperature.scales.Scale;

public class Model {
    private Scale[] scales;

    public Model(Scale[] scales) {
        this.scales = scales;
    }

    public Scale[] getScales() {
        return scales;
    }

    public double convert(Object from, Object to, double temperature) {
        Scale scaleFrom = (Scale) from;
        Scale toScale = (Scale) to;

        return toScale.convertFromCelsius(scaleFrom.convertToCelsius(temperature));
    }
}
