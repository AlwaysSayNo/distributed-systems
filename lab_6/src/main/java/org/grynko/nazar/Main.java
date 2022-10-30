package main.java.org.grynko.nazar;

import main.java.org.grynko.nazar.cannon.CannonMultiplying;
import main.java.org.grynko.nazar.fox.FoxMultiplying;
import main.java.org.grynko.nazar.simple.SimpleMultiplying;
import main.java.org.grynko.nazar.tape.TapeMultiplying;

import java.util.Arrays;
import java.util.Random;

public class Main {
    private static final int[] SIZES = {10, 100, 500, 1000};

    public static void main(String[] args){
        Random random = new Random();
        long start;
        long end;
        System.out.println("N\t\tSIMPLE\t\tTAPE(2)\t\tTAPE(4)\t\tFOX(2)\t\tFOX(4)\t\tCANNON(2)\tCANNON(4)");
        for (int size : SIZES) {
            int[][] a = new int[size][size];
            int[][] b = new int[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    a[i][j] = random.nextInt(10);
                    b[i][j] = random.nextInt(10);
                }
            }

            long[] results = new long[7];
            // ---------------
            start = System.currentTimeMillis();

            int[][] simple = SimpleMultiplying.multiply(a, b);

            end = System.currentTimeMillis();
            results[0] = end - start;
            // ---------------
            start = System.currentTimeMillis();

            TapeMultiplying tm2 = new TapeMultiplying(2);
            int[][] tape2 = tm2.multiply(a, b);

            end = System.currentTimeMillis();
            results[1] = end - start;
            // ---------------
            start = System.currentTimeMillis();

            TapeMultiplying tm4= new TapeMultiplying(2);
            int[][] tape4 = tm2.multiply(a, b);

            end = System.currentTimeMillis();
            results[2] = end - start;
            // ---------------
            start = System.currentTimeMillis();

            FoxMultiplying fm2= new FoxMultiplying(2);
            int[][] fox2 = fm2.multiply(a, b);

            end = System.currentTimeMillis();
            results[3] = end - start;
            // ---------------
            start = System.currentTimeMillis();

            FoxMultiplying fm4= new FoxMultiplying(4);
            int[][] fox4 = fm4.multiply(a, b);

            end = System.currentTimeMillis();
            results[4] = end - start;
            // ---------------
            start = System.currentTimeMillis();

            CannonMultiplying cm2 = new CannonMultiplying(2);
            int[][] cannon2 = cm2.multiply(a, b);

            end = System.currentTimeMillis();
            results[5] = end - start;
            // ---------------
            start = System.currentTimeMillis();

            CannonMultiplying cm4= new CannonMultiplying(4);
            int[][] cannon4 = cm4.multiply(a, b);

            end = System.currentTimeMillis();
            results[6] = end - start;
            // ---------------
            System.out.println(
                    "\nTESTS:"
                            + "\tTape 2: " + Arrays.deepEquals(simple, tape2)
                            + "\t\tTape 4: " + Arrays.deepEquals(simple, tape4)
                            + "\t\tFox 2: " + Arrays.deepEquals(simple, fox2)
                            + "\t\tFox 4: " + Arrays.deepEquals(simple, fox4)
                            + "\t\tCannon 2: " + Arrays.deepEquals(simple, cannon2)
                            + "\t\tCannon 4: " + Arrays.deepEquals(simple, cannon4));

            System.out.print(size);
            for (long r : results) {
                System.out.print("\t\t" + r + " ms");
            }
            System.out.println();

        }

    }

}
