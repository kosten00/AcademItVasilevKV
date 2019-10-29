package ru.academits.vasilev.graph;

import java.util.Arrays;
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

    public MyGraph(int maxVertexes) {
        this.maxVertexes = maxVertexes;

        graphMatrix = new int[maxVertexes][maxVertexes];
        for (int i = 0; i < maxVertexes; i++) {
            for (int j = 0; j < maxVertexes; j++) {
                graphMatrix[i][j] = 0;
            }
        }

        vertexes = (Vertex<T>[]) new Vertex[maxVertexes];
        for (int i = 0; i < maxVertexes; i++) {
            vertexes[i] = new Vertex<>();
        }
    }

    public void addVertex(T data) {
        vertexes[vertexesCount].setData(data);
        vertexesCount++;

        if (vertexesCount >= maxVertexes) {
            throw new IndexOutOfBoundsException("Max vertexes reached in the graph!");
        }
    }

    public void addEdge(int start, int end) {
        if (start == end) {
            throw new IllegalArgumentException("Impossible to connect vertex with itself!");
        }
        graphMatrix[start][end] = 1;
        graphMatrix[end][start] = 1;
    }

    public void visitInDepth() {
        vertexes[0].setVisited(true);

        System.out.println(vertexes[0]);

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int v = getVertexToVisit(stack.peek());

            if (v == -1) {
                stack.pop();
            } else {
                vertexes[v].setVisited(true);
                System.out.println(vertexes[v]);
                stack.push(v);
            }
        }

        for (int i = 0; i < vertexesCount; i++) {
            vertexes[i].setVisited(false);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(vertexes);
    }
}
