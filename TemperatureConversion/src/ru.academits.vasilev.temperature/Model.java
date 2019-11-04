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

    double convert(Scale from, Scale to, double temperature) {
        return to.convertFromCelsius(from.convertToCelsius(temperature));
    }
}