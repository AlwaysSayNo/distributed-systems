package main.java.org.grynko.nazar.tape;

import main.java.org.grynko.nazar.AbstractTask;

class Tape extends AbstractTask {

    public Tape(int tapeNumber, int step, int[][] a, int[][] b, int[][] result) {
        super(tapeNumber, step, a, b, result);
    }

    @Override
    public void run() {
        int size = a.length;

        for (int row = tapeNumber * step; row < (tapeNumber + 1) * step && row < size; row++) {
            int column = row;

            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    result[row][column] += a[row][j] * b[j][column];
                }

                column = (column + 1) % size;
            }
        }
    }

}
