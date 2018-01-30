package com.company;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {

    private ArrayList<Vertex> globalNeighbourhood;

    // This graph will contain vertexes.
    public Graph(int numNodes){
        this.globalNeighbourhood = new ArrayList<Vertex>();
        populateGraph(numNodes);
        setNeighbourhood((float)Math.random());
        vector = generateVector();
        jumpValue = (int)Math.sqrt((double)vector.length); //@todo: find nicer way of doing this.

    }

    // these are global so they can be used by multiple methods.
    int[] vector;
    int jumpValue;

    public float averageOfVectorsAtDimensionI(int i){
        // scan through neighbourhood, add up total at index i, at end average it out and int[] at i = avg at that dimention.
        //NB : final value in int[] is the global avg.
        System.out.println("Vector Length:"+vector.length);
        System.out.println("Jump Value:"+jumpValue);
        System.out.println("Current Dimension:"+i);

        float runningTotal = 0;
        for(int j = i; j<vector.length; j+= jumpValue ){
            System.out.println("Current Iteration Jump Start Index: "+j);

            runningTotal += vector[j];
            System.out.println("Vector at dimension J value: "+vector[j]);
            System.out.println("Running Total:" +runningTotal);
        }
        System.out.println("END OF CYCLE: Running Total Was:"+runningTotal+". Running Total / Jump Value Was : "+ runningTotal/jumpValue);

        return runningTotal/(float)jumpValue;
    }

    public float[] averageOfGraphForAllDimensions(){
        float[] averages = new float[jumpValue];
        for(int i=0; i<jumpValue; i++){
            averages[i] = averageOfVectorsAtDimensionI(i);
        }
        return averages;
    }

    public float graphAverage(){
        float graphAverageSum =0;
        float[] averages = averageOfGraphForAllDimensions();
        for(float i: averages){
            graphAverageSum += i;
        }
        return graphAverageSum / jumpValue;
    }


    public boolean addVertex(Vertex v) {
        return globalNeighbourhood.add(v);
    }

    public boolean removeVertex(Vertex v) {
        return globalNeighbourhood.remove(v);
    }


    public boolean populateGraph(int size) {
        long time = System.nanoTime();
        for (int i = 0; i < size; i++) {
            globalNeighbourhood.add(new Vertex());
        }
       // System.out.println("Graph Population Cost: "+(System.nanoTime()-time));
        return true;
    }

    public boolean setNeighbourhood(double probability) {

        long time = System.nanoTime();
        for (Vertex v : globalNeighbourhood) {
            for (Vertex v2 : globalNeighbourhood) {
                setNeighbourRelations(probability, v, v2);
            }
        }
      //  System.out.println("Generating Neighbourhood Of Entire Graph Cost: "+ (System.nanoTime()-time));
        return true;
    }

    public boolean setNeighbourRelations(double probability, Vertex a, Vertex b) {
        if (!a.equals(b)) {
            if (Math.random() < probability) {
                if (!(a.containsNeighbour(b) || b.containsNeighbour(a))) {
                    //passes to vertex method which itself returns a boolean.
                    return a.addNeighbour(b) && b.addNeighbour(a);
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public boolean clearNeighbourhood() {
        globalNeighbourhood.clear();
        return true;
    }

    public int getNeighbourhoodSize() {
        return globalNeighbourhood.size();
    }

    public ArrayList<Vertex> getNeighbourhood() {
        return this.globalNeighbourhood;
    }



    public int[][] generateMatrix() {
        long time = System.nanoTime();
        int size = getNeighbourhoodSize();
        int[][] returnArr = new int[size][size];

        int i = 0;
        for (Vertex v1 : globalNeighbourhood) {
            for (Vertex v2 : globalNeighbourhood) {
                if (v1.containsNeighbour(v2)) {
                    returnArr[i / size][i % size] = 1;
                }
                i++;
            }
        }
      //  System.out.println("Generating Matrix Version Cost: "+(System.nanoTime()-time));

        return returnArr;

    }



    public int calculateDiagonal(int index, int size){
        int i = index/size;
        int j = index%size;
        return calculateIndex((size-j), (size-i), size);
    }

    public int calculateIndex(int i, int j, int length){
        return length*(i-1)+j-1;
    }


    public List<int[]> getCols(){
        return new ArrayList<>();
    }

    public int[] generateVector() {
        return Arrays.stream(generateMatrix())
                .flatMapToInt(Arrays::stream)
                .toArray();
    }


    public int[][] transpose(int[][] matrix){
        long time = System.nanoTime();
        int dimension = globalNeighbourhood.size();
        int[][] flipped = new int[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                flipped[i][j] = matrix[j][i];
            }
        }
       // System.out.println("Matrix Transposing Cost: "+(System.nanoTime()-time));
        return flipped;
    }

    //verification that the output is consistently formatted when rendered as matrix


    public boolean isMatrixDiagonal(int[][] matrix) throws Exception {

        long time = System.nanoTime();

        for (int i = 0; i < globalNeighbourhood.size(); i++) {
            for (int j = 0; j < globalNeighbourhood.size(); j++) {

                if (matrix[i][j] != transpose(matrix)[i][j]) {
                    throw new Exception("Matrix was invalid when tested for its diagonal");
                }
            }
        }

        System.out.println("Checking Matrix Symmetric Cost: "+(System.nanoTime()-time));

        return true;

    }


    public void printMatrix() {

        try {
            long time = System.nanoTime();
            int[][] matrix = generateMatrix();
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (i == 0 && j == 0 || i == matrix.length - 1 && j == 0) {
                        System.out.print("[ ");
                    } else if (i != 0 && j == 0) {
                        System.out.print("| ");
                    }

                    System.out.print(matrix[i][j]);

                    if (i == 0 && j == matrix.length - 1 || i == matrix.length - 1 && j == matrix.length - 1) {
                        System.out.print(" ]");
                    } else if (i != 0 && j == matrix.length - 1) {
                        System.out.print(" |");
                    }
                }
                System.out.print("\n");
            }
         //   System.out.println("Printing Matrix To System Out Cost: "+(System.nanoTime()-time));
            isMatrixDiagonal(matrix);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Stack traceback:");
            System.out.println(e.getStackTrace());
        }
    }

}

