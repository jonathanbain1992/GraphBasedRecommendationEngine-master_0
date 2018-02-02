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

        ArrayList<Universe> universes = new ArrayList<>();

        for (int i = 0; i < numUniverses; i++) {
            universes.add(initUniverse(numGraphs, numNodes));
        }

      //  for (Universe uni: universes){
          //  uni.printAllGraphMatrices();
       // }

      //  for(Universe uni:universes){
           // uni.printAllVectors();
         //   uni.printAllGraphAverages();
       // }
        for(Universe uni : universes){
            int[][] vectors = uni.getAllVectors();
           // for(int i=0; i< vectors.length; i++){
               // System.out.println(Arrays.toString(vectors[i]));
            System.out.println(Arrays.toString(uni.getVectorAverages(uni.getAllVectors())));
            }
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

