package src.main.org.grynko.nazar.b;

public class StealingOfProperty {

    private static final int CAPACITY = 5;
    private static final int AMOUNT_DETAILS = 25;

    public static void main(String[] args) {
        ProducerConsumerCounter pcc = new ProducerConsumerCounter(CAPACITY);

        Runnable ivanovRunnable = () -> {
            try {
                pcc.produce(AMOUNT_DETAILS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable petrovRunnable = () -> {
            try {
                pcc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable nechiporchukRunnable = () -> {
            try {
                pcc.count();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };


        ThreadGroup thieves = new ThreadGroup("Thieves");
        new Thread(thieves, ivanovRunnable, "Ivanov").start();
        new Thread(thieves, petrovRunnable, "Petrov").start();
        new Thread(thieves, nechiporchukRunnable, "Nechiporchuk").start();

        while(thieves.activeCount() != 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n\nThe warehouse was looted.");

    }

}
