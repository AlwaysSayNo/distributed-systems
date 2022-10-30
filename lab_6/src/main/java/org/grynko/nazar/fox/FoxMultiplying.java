package main.java.org.grynko.nazar.fox;

import main.java.org.grynko.nazar.AbstractMultiplying;

public class FoxMultiplying extends AbstractMultiplying {

    public FoxMultiplying(int threadAmount) {
        super(threadAmount);
    }

    @Override
    protected Runnable getRunnable(int i, int[][] a, int[][] b, int[][] result) {
        int step = (int) Math.ceil(a.length / (double) threadAmount);
        return new FoxTask(i,step, a, b, result);
    }

}