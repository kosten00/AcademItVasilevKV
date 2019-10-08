package ru.academits.vasilev.lambda.main;

import ru.academits.vasilev.lambda.Person;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Comparator.*;

/*
Создать класс Person с полями имя и возраст. Сделать конструктор,
который принимает эти параметры. Сделать геттеры для полей
• В ru.academits.vasilev.temperature.main создать список из нескольких людей
• При помощи лямбда-функций:
• А) получить список уникальных имен
• Б) вывести список уникальных имен в формате:
Имена: Иван, Сергей, Петр.
• В) получить список людей младше 18, посчитать для них средний
возраст
• Г) при помощи группировки получить Map, в котором ключи –
имена, а значения – средний возраст
• Д) получить людей, возраст которых от 20 до 45, вывести в консоль
их имена в порядке убывания возраста
Задача 2
• Создать бесконечный поток корней чисел. С консоли
прочитать число – сколько элементов нужно вычислить,
затем – распечатать эти элементы
• * Попробовать реализовать бесконечный поток чисел
Фиббоначчи
 */

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

        personList.stream().filter(person -> person.getAge() < 18).forEach(System.out::println);

        //personList.stream().distinct().forEach(System.out::println);
    }
}
