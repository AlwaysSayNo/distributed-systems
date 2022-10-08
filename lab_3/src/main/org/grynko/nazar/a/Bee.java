package main.org.grynko.nazar.a;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Bee implements Runnable {

    private static final int DELAY = 300;

    private final String name;
    private final Pot pot;

    public Bee(String name, Pot pot) {
        this.name = name;
        this.pot = pot;
    }

    @Override
    public void run() {
        int amount = 1;
        while(!Thread.interrupted()) {
            try {
                randomSleep();
                pot.put(amount, this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public String getName() {
        return name;
    }

    private void randomSleep() throws InterruptedException {
        Random random = new Random();
        Thread.sleep(random.nextInt(DELAY) + 200);
    }

}

class Parent {

    private static final int A = 0;

    public static void main(String[] args) {
         Runnable a = () -> {
             for(int i = 0; i < 1; ++i) {
                 try {
                     Thread.currentThread().wait();
                 } catch (InterruptedException e) {
                     System.out.println("Interrupted");
                 }
                 System.out.println("Await");
             }
         };

         Runnable b = () -> {
             for (int i = 0; i < 100_000; ++i) {
                 Thread.currentThread().notifyAll();
             }
         };

         new Thread(a).start();
         new Thread(b).start();

    }

}

class Child extends Parent {

    static volatile int a = 0;
    static volatile Integer A = 0;

    public static void main(String[] args) {

        Runnable r = new Runnable() {

            @Override
            public void run() {
                for(int i = 0; i < 100_000; ++i) {
                    increment();
                }
                System.out.println(a + " " + A);
            }
        };

        new Thread(r).start();
        new Thread(r).start();


    }

    public static void increment() {
        a++;
        A++;
    }

}