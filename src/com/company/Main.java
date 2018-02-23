package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number of universes.");
        int numUniverses = sc.nextInt();

        System.out.println("Please enter a number of Graphs within each universe.");
        int numGraphs = sc.nextInt();

        System.out.println("Please enter a number of nodes within each graph.");
        int numNodes = sc.nextInt();

        //outermost data structure
        ArrayList<Universe> universes = new ArrayList<>();

        for (int i = 0; i < numUniverses; i++) {
            universes.add(initUniverse(numGraphs, numNodes));
        }

        //output logic for myself while in development.

        for(Universe u: universes){
            for(Graph g : u.globalNeighbourhood){
                System.out.println(g.validVectors((float)0.60));
            }
        }

        // /output logic.
    }

    public static Graph initGraph(int numNodes) {
        Graph g = new Graph(numNodes);
        return g;
    }

    public static Universe initUniverse(int numGraphs, int numNodes){
        Universe uni = new Universe();

        for (int i=0; i< numGraphs; i++){
            uni.addGraph(initGraph(numNodes));
        }
        return uni;
    }

}

