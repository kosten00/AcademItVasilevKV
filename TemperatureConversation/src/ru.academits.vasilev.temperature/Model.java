package ru.academits.vasilev.temperature;

public class Model {
    private String inputTemperature;
    private String fromScale;
    private String toScale;

    private final double CELSIUS_TO_FAHRENHEIT = (Double.parseDouble(inputTemperature) * (9. / 5.)) + 32.;
    private final double FAHRENHEIT_TO_CELSIUS = (Double.parseDouble(inputTemperature) - 32.) * (5. / 9.);

    public void setInputTemperature(String inputTemperature) {
        this.inputTemperature = inputTemperature;
    }

    public String getInputTemperature() {
        return inputTemperature;
    }

    public Model(String inputTemperature, String fromScale, String toScale) {
        this.inputTemperature = inputTemperature;
        this.fromScale = fromScale;
        this.toScale = toScale;
    }
}
