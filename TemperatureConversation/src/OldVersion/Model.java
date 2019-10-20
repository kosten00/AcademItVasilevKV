package OldVersion;

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
        if (fromScale.equals(toScale)) {
            return Double.toString(inputTemperature);
        }

        if (fromScale.equals("celsius")) {
            if (toScale.equals("fahrenheit")) {
                return Double.toString(Math.round(((inputTemperature * (9. / 5)) + 32) * 100d) / 100d);
            } else {
                return Double.toString(Math.round(((inputTemperature + 273.15)) * 100d) / 100d);
            }
        }

        if (fromScale.equals("fahrenheit")) {
            if (toScale.equals("celsius")) {
                return Double.toString(Math.round(((inputTemperature - 32) * (5. / 9)) * 100d) / 100d);
            } else {
                return Double.toString(Math.round(((inputTemperature - 32) * (5. / 9) + 273.15) * 100d) / 100d);
            }
        }

        if (toScale.equals("celsius")) {
            return Double.toString(Math.round((inputTemperature - 273.15) * 100d) / 100d);
        }

        return Double.toString(Math.round(((inputTemperature - 273.15) * (9. / 5) + 32) * 100d) / 100d);
    }
}