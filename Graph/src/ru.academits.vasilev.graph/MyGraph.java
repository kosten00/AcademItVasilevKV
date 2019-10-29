package ru.academits.vasilev.graph;

import java.util.Stack;

public class MyGraph<T> {
    private int maxVertexes;
    private int vertexesCount;
    private Vertex<T>[] vertexes;
    private int[][] graphMatrix;

    private int getVertexToVisit(int i) {
        for (int j = 0; j < vertexesCount; j++) {
            if (graphMatrix[i][j] == 1 && !vertexes[j].isVisited()) {
                return j;
            }
        }

        return -1;
    }

    public MyGraph(int graphSize) {
        this.maxVertexes = graphSize;

        graphMatrix = new int[graphSize][graphSize];
        for (int i = 0; i < graphSize; i++) {
            for (int j = 0; j < graphSize; j++) {
                graphMatrix[i][j] = 0;
            }
        }
    }

    public void addVertex(T data) {
        vertexesCount++;
        vertexes[vertexesCount] = new Vertex<T>(data);
    }

    public void addEdge(int start, int end) {
        graphMatrix[start][end] = 1;
        graphMatrix[end][start] = 1;
    }

    public void visitInDepth() {
        vertexes[0].setVisited(true);

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int v = getVertexToVisit(stack.peek());

            if (v == -1) {
                stack.pop();
            } else {
                vertexes[v].setVisited(true);
                stack.push(v);
            }
        }

        for (int i = 0; i < vertexesCount; i++) {
            vertexes[i].setVisited(false);
        }
    }
}
