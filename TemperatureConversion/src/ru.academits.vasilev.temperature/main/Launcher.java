package ru.academits.vasilev.temperature.main;

import ru.academits.vasilev.temperature.scales.Scale;
import ru.academits.vasilev.temperature.*;
import ru.academits.vasilev.temperature.scales.Celsius;
import ru.academits.vasilev.temperature.scales.Fahrenheit;
import ru.academits.vasilev.temperature.scales.Kelvin;

/*
3. В конце имени классов шкал лучше добавить слово Scale
4. if (from.equals(to))
У шкал не переопределен equals
5. Если нужно перевести строку, то нужно переносить вместе с точкой:
http://joxi.ru/V2VekyKhd6v9Em
6. Есть ошибка в переводе температур.
Например, неверно переводит из Кельвина в Цельсия.
И переменной list лучше дать более информативное имя
7. При проверке корректности ввода числа лучше всего просто использовать методы, например, Double.parseDouble.
Если парсится, то все верно
 */

public class Launcher {
    public static void main(String[] args) {
        new Controller(new Scale[]{new Celsius("celsius"),
                new Fahrenheit("fahrenheit"),
                new Kelvin("kelvin")});
    }
}