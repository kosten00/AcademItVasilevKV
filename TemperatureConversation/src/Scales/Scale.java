package Scales;


public abstract class Scale {
    private String name;

    public Scale(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double convertFromCelsius(double temperature);

    public abstract double convertToCelsius(double temperature);
}