package main.java.org.grynko.nazar.simple;

public class SimpleMultiplying {

    public static int[][] multiply(int[][] a, int[][] b){
        int size = a.length;
        int[][] result = new int[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                for(int k = 0; k < size; k++){
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }
}
