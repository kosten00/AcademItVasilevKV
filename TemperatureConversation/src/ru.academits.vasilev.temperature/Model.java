package ru.academits.vasilev.temperature;


import Scales.Scale;

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
        double conversionResult = 0;

        for (Scale scale : scales) {
            if (scale.getName().equals(from)) {
                conversionResult = scale.convertToCelsius(temperature);

                break;
            }
        }

        for (Scale scale : scales) {
            if (scale.getName().equals(to)) {
                conversionResult = scale.convertFromCelsius(conversionResult);

                break;
            }
        }

        return conversionResult;
    }
}