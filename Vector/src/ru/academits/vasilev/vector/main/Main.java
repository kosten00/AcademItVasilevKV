package ru.academits.vasilev.vector.main;

import ru.academits.vasilev.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] arr = {2.1, 4.3, 5.1, 4.8};

        Vector v1 = new Vector(arr);

        System.out.println(v1);
    }
}
