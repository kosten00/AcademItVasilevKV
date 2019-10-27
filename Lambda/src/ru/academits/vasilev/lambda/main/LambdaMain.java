package ru.academits.vasilev.lambda.main;

import ru.academits.vasilev.lambda.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.averagingInt;

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

        //Creating list of unique names from personList.
        List<String> list = personList.stream().map(Person::getName).distinct().collect(Collectors.toList());
        System.out.println("Names: " + list.toString().replace("[", "").replace("]", "") + ".");
        System.out.println();

        //Creating list of persons younger than 18, count their average age:
        Stream<Person> stream1 = personList.stream().filter(p -> p.getAge() < 18);
        List<Person> youngerEighteen = stream1.collect(Collectors.toList());
        System.out.println(youngerEighteen.stream().mapToInt(Person::getAge).average());

        //Get Map with names as keys and average age as value.)
        Map<String, Double> map = personList.stream().collect(Collectors.groupingBy(Person::getName, averagingInt(Person::getAge)));
        map.forEach((name, age) -> System.out.printf("name: %s; average age: %s; ", name, age));

        System.out.println();
        //Get persons aged 20-45 y.o., print to the console their names in decreasing order:
        personList.stream().filter(person -> person.getAge() <= 45 && person.getAge() >= 20).
                sorted((p1, p2) -> p2.getAge() - p1.getAge()).
                forEach((p) -> System.out.println(p.getName()));

        //Create infinite stream of square roots of numbers:
        System.out.println("Input number: ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        DoubleStream.iterate(121, Math::sqrt).limit(number).forEach(System.out::println);
    }
}