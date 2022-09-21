package src.main.org.grynko.nazar.a;

import java.util.List;

public class Bee implements Runnable {

    private static final int DELAY = 200;

    private final String name;
    private final TaskPortfolio taskPortfolio;
    private final ThreadGroup threadGroup;

    public Bee(String name, TaskPortfolio taskPortfolio, ThreadGroup threadGroup) {
        this.name = name;
        this.taskPortfolio = taskPortfolio;
        this.threadGroup = threadGroup;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            List<Boolean> task = taskPortfolio.getNewTask();
            if(task == null) return;

            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                return;
            }
            boolean wasFound = findWinniThePooh(task);

            System.out.println("Bee " + name + " started search.");
            if(wasFound) {
                threadGroup.interrupt();
                taskPortfolio.wasFound(this);
                System.out.println("==> Bee " + name + " found Winnie the Pooh <==");

                return;
            }
        }
    }

    private boolean findWinniThePooh(List<Boolean> task) {
        for(Boolean cell : task) {
            if(cell) return true;
        }

        return false;
    }

    public String getName() {
        return name;
    }
}
