package src.main.org.grynko.nazar.a;

public class RevengeOfBees {

    private static final int BEES_AMOUNT = 100;
    private static final int FOREST_WIDTH = 500;
    private static final int FOREST_HEIGHT = 500;
    private static final int WINNIE_X = 250;
    private static final int WINNIE_Y = 250;

    public static void main(String[] args) {
        Forest forest = new Forest(FOREST_WIDTH, FOREST_HEIGHT);
        //forest.setWinnieThePooh(WINNIE_X, WINNIE_Y);
        TaskPortfolio taskPortfolio = new TaskPortfolio(forest);

        ThreadGroup threadGroup = new ThreadGroup("Bees");
        for(int i = 0; i < BEES_AMOUNT; ++i) {
            Bee bee = new Bee(String.valueOf(i), taskPortfolio, threadGroup);
            new Thread(threadGroup, bee).start();
        }

        while(threadGroup.activeCount() != 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n\n");
        if(taskPortfolio.isFound()) {
            System.out.println("Winnie the Pooh was found.");
            System.out.println("Task solved: " + taskPortfolio.getFoundingBee().getName());
        }
        else {
            System.out.println("Winnie the Pooh wasn't found.");
        }
    }

}
