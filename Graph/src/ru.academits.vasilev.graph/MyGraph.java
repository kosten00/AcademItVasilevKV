package ru.academits.vasilev.graph;

import java.util.*;
import java.util.function.Consumer;

public class MyGraph<T> {
    private int maxVertexes;
    private int vertexesCount;
    private Vertex<T>[] vertexes;
    private int[][] graphMatrix;

    private int getEdgedUnvisitedVertex(int i) {
        for (int j = 0; j < vertexesCount; j++) {
            if (graphMatrix[i][j] == 1 && !vertexes[j].isVisited()) {
                return j;
            }
        }

        return -1;
    }

    private void setAllUnvisited() {
        for (int i = 0; i < vertexesCount; i++) {
            vertexes[i].setVisited(false);
        }
    }

    private int checkUnvisitedVertex() {
        for (int i = 0; i < vertexesCount; i++) {
            if (!vertexes[i].isVisited()) {
                return i;
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
    }

    public void addVertex(T data) {
        vertexes[vertexesCount] = new Vertex<>(data);
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

    public void visitInDepth(Consumer<? super T> action) {
        if (action == null) {
            throw new NullPointerException("Consumer == null");
        }
        int start = checkUnvisitedVertex();

        if (start >= 0) {
            Vertex<T> vertex = vertexes[start];
            vertex.setVisited(true);

            action.accept(vertexes[start].getData());

            Stack<Integer> stack = new Stack<>();
            stack.push(start);

            while (!stack.isEmpty()) {
                int v = getEdgedUnvisitedVertex(stack.peek());

                if (v == -1) {
                    stack.pop();
                } else {
                    vertex = vertexes[v];
                    vertex.setVisited(true);

                    action.accept(vertexes[v].getData());

                    stack.push(v);
                }
            }
            visitInDepth(action);
        }

        setAllUnvisited();
    }


    public void visitInBreadth(Consumer<? super T> action) {
        if (action == null) {
            throw new NullPointerException("Consumer == null");
        }
        int start = checkUnvisitedVertex();

        if (start >= 0) {
            Vertex<T> vertex = vertexes[start];
            vertex.setVisited(true);

            action.accept(vertex.getData());

            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(start);

            int v2;
            while (!queue.isEmpty()) {
                int v1 = queue.remove();

                while ((v2 = getEdgedUnvisitedVertex(v1)) != -1) {
                    vertex = vertexes[v2];
                    vertex.setVisited(true);

                    action.accept(vertex.getData());

                    queue.add(v2);
                }
            }
            visitInBreadth(action);
        }

        setAllUnvisited();
    }

    @Override
    public String toString() {
        return Arrays.toString(vertexes);
    }
}