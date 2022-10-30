package main.java.org.grynko.nazar.tape;


import main.java.org.grynko.nazar.AbstractMultiplying;

public class TapeMultiplying extends AbstractMultiplying {

    public TapeMultiplying(int threadAmount) {
        super(threadAmount);
    }

    @Override
    protected Runnable getRunnable(int i, int[][] a, int[][] b, int[][] result) {
        int step = (int) Math.ceil(a.length / (double) threadAmount);
        return new Tape(i,step, a, b, result);
    }

}

