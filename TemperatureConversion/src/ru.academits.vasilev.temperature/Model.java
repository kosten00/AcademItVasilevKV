package ru.academits.vasilev.temperature;

import ru.academits.vasilev.temperature.scales.Scale;

class Model {
    private Scale[] scales;

    Model(Scale[] scales) {
        this.scales = scales;
    }

    Scale[] getScales() {
        return scales;
    }

    double convert(Object from, Object to, double temperature) {
        Scale scaleFrom = (Scale) from;
        Scale toScale = (Scale) to;

        return toScale.convertFromCelsius(scaleFrom.convertToCelsius(temperature));
    }
}