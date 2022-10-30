package main.java.org.grynko.nazar.cannon;

import main.java.org.grynko.nazar.AbstractMultiplying;

public class CannonMultiplying extends AbstractMultiplying {

    public CannonMultiplying(int threadAmount) {
        super(threadAmount);
    }

    @Override
    protected Runnable getRunnable(int i, int[][] a, int[][] b, int[][] result) {
        int step = (int) Math.ceil(a.length / (double) threadAmount);
        return new TaskCannon(i,step, a, b, result);
    }

}
