package main.java.org.grynko.nazar.fox;

import main.java.org.grynko.nazar.AbstractTask;

public class FoxTask extends AbstractTask {

    public FoxTask(int tapeNumber, int step, int[][] a, int[][] b, int[][] result) {
        super(tapeNumber, step, a, b, result);
    }

    @Override
    public void run() {
        int size = a.length;

        for (int row = tapeNumber * step; row < (tapeNumber + 1) * step && row < size; row++) {
            int b_i = row;
            int a_j = row;

            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    result[row][j] += a[row][a_j] * b[b_i][j];
                }

                a_j = (a_j + 1) % size;
                b_i = (b_i + 1) % size;
            }
        }
    }
}
