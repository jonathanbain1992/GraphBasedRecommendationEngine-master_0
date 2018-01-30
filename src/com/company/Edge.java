package com.company;

public class Edge {

    private int weight;
    private Vertex neighbour;

    public Edge(Vertex neighbour){
        this.neighbour = neighbour;
        this.weight = 1;
    }

    //method if we want to specify a weight of the edge in future.
    public Edge(Vertex neighbour, int weight){
        this.neighbour = neighbour;
        this.weight = weight;
    }

    public int getWeight(){
        return this.weight;
    }

    public boolean setWeight(int weight){
         this.weight = weight;
         return true;
    }

    public Vertex getNeighbour(){
        return this.neighbour;
    }

    public boolean setNeighbour(Vertex neighbour){
        this.neighbour = neighbour;
        return true;
    }


}
