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

    public String convert() {
        if (fromScale.equals("celsiusButtonFrom")) {
            if (toScale.equals("fahrenheitButtonTo")) {
                return Double.toString((inputTemperature * (9. / 5)) + 32);
            }
        }

        if (fromScale.equals("fahrenheitButtonFrom")) {
            if (toScale.equals("celsiusButtonTo")) {
                return Double.toString((inputTemperature - 32) * (5. / 9));
            }
        }

        return "missed";
    }
}