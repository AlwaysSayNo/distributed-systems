package main.org.grynko.nazar.b;

import java.util.Random;

public class Client implements Runnable {

    private String name;
    private Hairdresser hairdresser;
    public static final int WAIT_TIME_BOTTOM = 1000;
    public static final int WAIT_TIME_RANDOM = 1000;

    public Client(String name, Hairdresser hairdresser) {
        this.name = name;
        this.hairdresser = hairdresser;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            try {
                System.out.println("Client " + name + " waits.");
                hairdresser.shearMan(this);
                Thread.sleep(new Random().nextInt(WAIT_TIME_RANDOM) + WAIT_TIME_BOTTOM);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return name;
    }
}
