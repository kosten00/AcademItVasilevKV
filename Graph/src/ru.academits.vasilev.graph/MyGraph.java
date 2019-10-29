package ru.academits.vasilev.graph;

public class MyGraph<T> {
    private int maxVertexes;
    private int vertexesCount;
    private Vertex<T> vertexes[];
    private int[][] graphMatrix;

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
}
