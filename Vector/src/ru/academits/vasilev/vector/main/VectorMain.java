package ru.academits.vasilev.vector.main;

import ru.academits.vasilev.vector.Vector;

import java.util.Arrays;

public class VectorMain {
    public static void main(String[] args) {
        System.out.println("non-static methods:");

        double[] array1 = {11, -1, 9, 34, 45};
        double[] array2 = {3, -4, 0};

        System.out.println("arrays to test Vector class: " + Arrays.toString(array1) + ", " + Arrays.toString(array2));

        //Разными способами создаем новые экземпляры класса
        Vector vector1 = new Vector(6); //размерность n, все компоненты равны 0
        System.out.println("vector1 with size of 6 created " + vector1);
        Vector vector2 = new Vector(vector1); //конструктор копирования
        System.out.println("vector2 is a copy of vector1 " + vector2);
        Vector vector3 = new Vector(array1); //заполнение вектора значениями из массива
        System.out.println("vector3 created with array1 " + vector3);
        Vector vector4 = new Vector(7, array2); //заполнение вектора размерностью n значениями из массива.

        //узнаём размерность вектора
        System.out.println("vector3 have size of " + vector3.getSize());

        //Прибавление к вектору другого вектора
        System.out.print("vector3 " + vector3 + " + vector4 " + vector4 + " = ");
        vector3.sum(vector4);
        System.out.println(vector3);

        vector3 = new Vector(array1);

        //Вычитание из вектора другого вектор
        System.out.println("vector3 " + vector3 + " - vector4 " + vector4 + " = ");
        vector3.subtract(vector4);
        System.out.println(vector3);

        //Умножение вектора на скаляр
        System.out.print("vector3 " + vector3 + " multiply by double 10: ");
        vector3.multiplyByScalar(10.0);
        System.out.println(vector3);

        //Разворот вектора (умножение всех компонент на -1
        System.out.print("vector4 " + vector4);
        vector4.revert();
        System.out.println(" revert: " + vector4);

        //Получение длины вектора
        System.out.println("vector3 " + vector3 + " length = " + vector3.getLength());

        //Получение и установка компоненты вектора по индексу
        System.out.println("put element = " + 777.0 + " by index " + 2 + " in vector3 " + vector3 + ". Now vector3 is ");
        vector3.setElement(2, 777);
        System.out.println(vector3);

        System.out.println("element with index " + 4 + " from vector3 is " + vector3.getElement(4));

        System.out.println("static methods: ");

        Vector test1 = new Vector(array1);
        Vector test2 = new Vector(array2);

        //Сложение двух векторов – должен создаваться новый вектор
        Vector vector5 = Vector.getVectorsSum(test1, test2);
        System.out.println("vector5 " + vector5 + " created from test1 " + test1 + " and test2 " + test2 + " by sum");

        //Вычитание векторов – должен создаваться новый вектор
        Vector vector6 = Vector.getVectorsSubtraction(test1, test2);
        System.out.println("vector6 " + vector6 + " created from test1 " + test1 + " and test2 " + test2 + " by subtraction");

        //Скалярное произведение векторов
        System.out.println("scalar product of vector test1" + test1 + " and test2 " + test2 + " is " + Vector.getScalarMultiplication(test1, test2));
    }
}