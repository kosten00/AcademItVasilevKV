package ru.academits.vasilev.graph.main;

/*
Лекции, нужные для решения задачи: 1-8, 14.
Реализовать обход несвязного графа в ширину и глубину.
Граф просто задаем двумерным массивом.
Чтобы реализовать обходы для несвязного графа надо просто посмотреть массив visited после обхода очередной компоненты связности.
Если остались не visited вершины, запускаем алгоритм для них и т.д., пока такие вершины не кончатся.
Чему научитесь:
Понимание как работать с графами
 */

import ru.academits.vasilev.graph.MyGraph;

public class MyGraphMain {
    public static void main(String[] args) {
        MyGraph<String> graph1 = new MyGraph<>(10);

        graph1.addVertex("a");
        graph1.addVertex("b");
        graph1.addVertex("c");
        graph1.addVertex("d");
        graph1.addVertex("e");
        graph1.addVertex("f");
        graph1.addVertex("g");
        graph1.addVertex("h");
        graph1.addVertex("i");

        graph1.addEdge(0,1);
        graph1.addEdge(1,2);
        graph1.addEdge(2,3);
        graph1.addEdge(3,4);
        graph1.addEdge(4,5);
        graph1.addEdge(5, 6);
        graph1.addEdge(6,7);
        graph1.addEdge(7,8);
        graph1.addEdge(8,9);

        graph1.visitInDepth();
    }
}
