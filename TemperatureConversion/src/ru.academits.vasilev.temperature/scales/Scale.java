package ru.academits.vasilev.temperature.scales;


import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scale scale = (Scale) o;
        return name.equals(scale.name);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + name.hashCode();
        return hash;
    }
}