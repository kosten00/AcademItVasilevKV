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

    public void setFromScale(String fromScale) {
        this.fromScale = fromScale;
    }

    public void setToScale(String toScale) {
        this.toScale = toScale;
    }

    public String convert() {


        return "";
    }

    public Model() {
    }
}
