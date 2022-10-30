package main.java.org.grynko.nazar;

public abstract class AbstractTask implements Runnable {

    protected final int tapeNumber;
    protected final int step;
    protected final int[][] a;
    protected final int[][] b;
    protected final int[][] result;

    public AbstractTask(int tapeNumber, int step, int[][] a, int[][] b, int[][] result) {
        this.tapeNumber = tapeNumber;
        this.step = step;
        this.a = a;
        this.b = b;
        this.result = result;
    }
}
