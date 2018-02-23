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

    public int[][] getAllVectors(){
        int[][] vectors = new int[globalNeighbourhood.size()][globalNeighbourhood.get(0).getNeighbourhoodSize()];

        for (int i=0; i<globalNeighbourhood.size(); i++){
            vectors[i] = globalNeighbourhood.get(i).generateVector();
        }

        return vectors;
    }

    public float[] getVectorAverages(int[][] vectors){

        float size = globalNeighbourhood.size(); //stored here to save on repeated calls, should be size of each vector.
        float[] avg = new float[globalNeighbourhood.get(0).jumpValue]; //should be the size of each vector

        for(int dimension=0; dimension<globalNeighbourhood.get(0).jumpValue; dimension++){ //coupling between dimension and size
            for(int vector=0; vector<size; vector++){
                avg[dimension] += vectors[vector][dimension];
            }
            avg[dimension]/=size; //take total, convert to avg.
        }

        return avg;
    }

    public void printAllGraphAverages(){

        for( Graph g : globalNeighbourhood){
            System.out.println(               "*****GRAPH AVERAGE*****");
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
