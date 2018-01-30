package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Universe {

    public ArrayList<Graph> globalNeighbourhood;

    // The universe contains graphs in its neighbourhood.
    public Universe(){
        globalNeighbourhood = new ArrayList<>();
    }

    public Universe(Graph... graph) {
        globalNeighbourhood = (ArrayList<Graph>) Arrays.asList(graph);
    }

    public void printAllVectors(){
        for (Graph g : globalNeighbourhood){
            System.out.println(Arrays.toString(g.generateVector()));
        }
    }

    public void printAllGraphAverages(){
        for( Graph g : globalNeighbourhood){
            System.out.println(               "*****GRAPH AVERAGE*****");
          //  System.out.println("              "+g.graphAverage());
            System.out.println(Arrays.toString(g.averageOfGraphForAllDimensions()));
            System.out.println(               "***********************");
        }
    }

    public boolean addGraph(Graph g) {
        return globalNeighbourhood.add(g);
    }

    public boolean removeGraph(Graph g){
        return globalNeighbourhood.remove(g);
    }

    public Graph getGraph(int index){
        return globalNeighbourhood.get(index);
    }

    public void setGraph(int index, Graph graph){
        globalNeighbourhood.set(index, graph);
    }

    public void printGraphMatrix(int index){
        globalNeighbourhood.get(index).printMatrix();
    }

    public int[] graphEdgeConnectionVector(int index){
        return globalNeighbourhood.get(index).generateVector();
    }

    public void printAllGraphMatrices(){
        for (Graph g: globalNeighbourhood){
            g.printMatrix();
            System.out.println("-----\n");
        }
    }
}
