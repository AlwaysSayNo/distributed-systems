package src.main.org.grynko.nazar.a;

import java.util.List;

public class TaskPortfolio {

    private final Forest forest;
    private int currentTaskNumber;
    private boolean isFound;
    private Bee foundingBee;

    public TaskPortfolio(Forest forest) {
        this.forest = forest;
        this.currentTaskNumber = 0;
        this.isFound = false;
    }

    public synchronized List<Boolean> getNewTask() {
        if(isFinished()) return null;

        List<Boolean> task = forest.getHectares().get(currentTaskNumber);
        currentTaskNumber++;
        return task;
    }

    public synchronized boolean isFinished() {
        return isFound || currentTaskNumber >= forest.getHectares().size();
    }

    public synchronized boolean isFound() {
        return isFound;
    }

    public void wasFound(Bee foundingBee) {
        if(!isFound) {
            isFound = true;
            this.foundingBee = foundingBee;
        }
    }

    public Bee getFoundingBee() {
        return foundingBee;
    }

}
