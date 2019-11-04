package ru.academits.vasilev.graph;

class Vertex<T> {
    private T data;
    private boolean visited;

    Vertex(T data) {
        this.data = data;
        visited = false;
    }

    Vertex() {
        visited = false;
    }

    T getData() {
        return data;
    }

    void setData(T data) {
        this.data = data;
    }

    boolean isVisited() {
        return visited;
    }

    void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
