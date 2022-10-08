package main.org.grynko.nazar.b;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerCounter {

    private final BlockingQueue<Integer> fromProducerToConsumer;
    private final BlockingQueue<Integer> fromConsumerToCounter;
    private static final Integer STOP_SIGNAL = -1;

    public ProducerConsumerCounter(int capacity) {
        this.fromProducerToConsumer = new ArrayBlockingQueue<>(capacity, true);
        this.fromConsumerToCounter = new ArrayBlockingQueue<>(capacity, true);
    }

    public void produce(int amount) throws InterruptedException {
        if(amount < 0) throw new InterruptedException();

        int detailsNumber = 1;
        while(detailsNumber <= amount) {
            fromProducerToConsumer.put(detailsNumber);
            System.out.println(Thread.currentThread().getName() + " takes detail number " + detailsNumber + " out of the warehouse.");

            detailsNumber++;
            randomSleep();
        }

        fromProducerToConsumer.put(STOP_SIGNAL);
        System.out.println(Thread.currentThread().getName() + " says that all the details are out. <==");

    }

    public void consume() throws InterruptedException {
        int currDetail = fromProducerToConsumer.take();

        while (currDetail != STOP_SIGNAL) {
            fromConsumerToCounter.put(currDetail);
            System.out.println(Thread.currentThread().getName() + " puts detail number " + currDetail + " in the truck.");

            currDetail = fromProducerToConsumer.take();

            randomSleep();
        }

        fromConsumerToCounter.put(currDetail);
        System.out.println(Thread.currentThread().getName() + " says all the details are already in the truck. <==");
    }

    public void count() throws InterruptedException {
        int counter = 0;
        int currDetail = fromConsumerToCounter.take();

        while (currDetail != STOP_SIGNAL) {
            counter++;
            System.out.println(Thread.currentThread().getName() + " has already counted so many things: " + counter + ".");

            randomSleep();
            currDetail = fromConsumerToCounter.take();
        }

        System.out.println(Thread.currentThread().getName() + " says he's counted all the details. <==");

    }

    private void randomSleep() throws InterruptedException {
        Random random = new Random();
        Thread.sleep(random.nextInt(400) + 200);
    }

}
