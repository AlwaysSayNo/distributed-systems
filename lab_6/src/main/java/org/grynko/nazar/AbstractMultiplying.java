package main.java.org.grynko.nazar;

public abstract class AbstractMultiplying {

    protected final int threadAmount;

    public AbstractMultiplying(int threadAmount) {
        this.threadAmount = threadAmount;
    }

    public int[][] multiply(int[][] a, int[][] b) {
        int size = a.length;
        int[][] result = new int[size][size];

        Thread[] tasks = new Thread[threadAmount];
        for(int i = 0; i < tasks.length; i++){
            Runnable runnable = getRunnable(i, a, b, result);
            tasks[i] = new Thread(runnable);

            tasks[i].start();
        }

        for (Thread task : tasks) {
            try {
                task.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    protected abstract Runnable getRunnable(int i, int[][] a, int[][] b, int[][] result);

}
