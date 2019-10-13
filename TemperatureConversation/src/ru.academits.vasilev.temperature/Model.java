package ru.academits.vasilev.temperature;

public class Model {
    private double inputTemperature;
    private String fromScale;
    private String toScale;

    public void setInputTemperature(double inputTemperature) {
        this.inputTemperature = inputTemperature;
    }

    public void setFromScale(String fromScale) {
        this.fromScale = fromScale;
    }

    public void setToScale(String toScale) {
        this.toScale = toScale;
    }

    public Model() {
    }

    public String convert() {
        double temp = inputTemperature;

        if (fromScale.equals("celsiusButtonFrom")) {
            if (toScale.equals("fahrenheitButtonTo")) {
                setFromScale(null);
                setToScale(null);
                return Double.toString((temp * (9. / 5.)) + 32.);
            }
        }

        if (fromScale.equals("fahrenheitButtonFrom")) {
            if (toScale.equals("celsiusButtonTo")) {
                setFromScale(null);
                setToScale(null);
                return Double.toString((temp - 32.) * (5. / 9.));
            }
        }

        return "missed";
    }
}