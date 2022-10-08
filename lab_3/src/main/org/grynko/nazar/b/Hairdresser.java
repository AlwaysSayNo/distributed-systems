package main.org.grynko.nazar.b;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Hairdresser {

    private final Lock lock = new ReentrantLock();

    public void shearMan(Client client) throws InterruptedException {
        lock.lock();

        System.out.println("The hairdresser cuts the visitor " + client.getName() + " hair.");
        Thread.sleep(new Random().nextInt(300) + 200);

        lock.unlock();
    }


}
