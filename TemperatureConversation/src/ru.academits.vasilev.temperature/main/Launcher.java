package ru.academits.vasilev.temperature.main;

import Scales.Scale;
import ru.academits.vasilev.temperature.*;

/*
1. Литералы такого вида лучше не использовать 9.
2. Сейчас в программе нарушен open closed принцип.
Если понадобится добавить еще 1 шкалу, то придется сильно менять существующий код.
В том числе придется писать код для перевода из каждой шкалы в каждую.
Нужно будет придумать решение, в котором легко добавлять/удалять шкалы.
Если долго не будет получаться, то можно будет спросить подсказку
3. Вызов SwingUtilities.invokeLater должен делаться во view
4. Контроллер не должен ничего знать про кнопки и т.д.
5. View не должен наследоваться от JFrame, т.к. не переопределяется ни один виртуальный метод
6. Лучше не стирать значение при переводе
7. Лучше добавить горизонтальные отступы вокруг кнопки, чтобы она не сливалась с остальными элементами формы
8. Модель по смыслу должна выдавать число, а не строку.
При этом не стоит округлять результат.
Результат лучше чтобы округлялся во view
 */

public class Launcher {
    public static void main(String[] args) {
        Scale celsius = new Scale("Celsius") {
            @Override
            public double convertFromCelsius(double temperature) {
                return temperature;
            }

            @Override
            public double convertToCelsius(double temperature) {
                return temperature;
            }
        };

        Scale fahrenheit = new Scale("Fahrenheit") {

            @Override
            public double convertFromCelsius(double temperature) {
                return Math.round(((temperature * (9. / 5)) + 32) * 100d) / 100d;
            }

            @Override
            public double convertToCelsius(double temperature) {
                return Math.round(((temperature - 32) * (5. / 9)) * 100d) / 100d;
            }
        };

        Scale kelvin = new Scale("Kelvin") {
            @Override
            public double convertFromCelsius(double temperature) {
                return Math.round(((temperature + 273.15)) * 100d) / 100d;
            }

            @Override
            public double convertToCelsius(double temperature) {
                return Math.round((temperature - 273.15) * 100d) / 100d;
            }
        };

        Scale[] scales = new Scale[]{celsius, fahrenheit, kelvin};

        NewController controller = new NewController(scales);

        NewModel model = new NewModel(controller);

        new NewView(model);
    }
}