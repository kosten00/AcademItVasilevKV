package ru.academits.vasilev.lambda.main;

import ru.academits.vasilev.lambda.Person;

import java.util.LinkedList;
import java.util.stream.Stream;

public class LambdaMain {
    public static void main(String[] args) {
        LinkedList<Person> personList = new LinkedList<>();

        personList.add(new Person("Саша", 12));
        personList.add(new Person("Маша", 25));
        personList.add(new Person("Глаша", 18));
        personList.add(new Person("Геннадий", 45));
        personList.add(new Person("Витя", 35));
        personList.add(new Person("Витя", 5));
        personList.add(new Person("Глаша", 19));


    }
}
