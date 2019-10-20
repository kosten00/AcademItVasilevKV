package ru.academits.vasilev.temperature;


import Scales.Scale;

public class NewModel {
    private NewController controller;

    public NewModel(NewController controller) {
        this.controller = controller;
    }

    public String[] getScaleNames() {
        String[] names = new String[controller.getScales().length];

        int i = 0;
        for (Scale scale : controller.getScales()) {
            names[i] = scale.getName();

            i++;
        }

        return names;
    }

    public double convert(String from, String to, double temperature) {
        double conversionResult = 0;

        for (Scale scale : controller.getScales()) {
            if (scale.getName().equals(from)) {
                conversionResult = scale.convertToCelsius(temperature);

                break;
            }
        }

        for (Scale scale : controller.getScales()) {
            if (scale.getName().equals(to)) {
                conversionResult = scale.convertFromCelsius(conversionResult);

                break;
            }
        }

        return conversionResult;
    }
}