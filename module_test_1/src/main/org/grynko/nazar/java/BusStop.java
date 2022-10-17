package main.org.grynko.nazar.java;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class BusStop {

    private final Semaphore semaphore;
    private final String name;

    public BusStop(int permits, String name) {
        this.semaphore = new Semaphore(permits, true);
        this.name = name;
    }

    @SneakyThrows
    public void stop() {
        this.semaphore.acquire();

        String busName = Thread.currentThread().getName();

        System.out.printf("%s has stopped at the bus stop '%s'.\n", busName, name);
        randomSleep();
        System.out.printf("%s has left the bus stop '%s'.\n", busName, name);

        this.semaphore.release();
    }

    @SneakyThrows
    private void randomSleep() {
        Random random = new Random();
        Thread.sleep(random.nextInt(200) + 300);
    }

}
