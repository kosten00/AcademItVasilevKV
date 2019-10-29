package ru.academits.vasilev.graph;

class Vertex<T> {
    private T data;
    private boolean visited;

    Vertex(T data) {
        this.data = data;
        visited = false;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

}
