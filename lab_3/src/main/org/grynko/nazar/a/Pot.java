package main.org.grynko.nazar.a;

public class Pot {

    private final int CAPACITY;
    private int size;

    public Pot(int capacity) {
        this.CAPACITY = capacity;
        this.size = 0;
    }

    public synchronized void put(int amount, Bee bee) throws InterruptedException {
        while(size >= CAPACITY) wait();

        size += amount;

        System.out.println("Bee " + bee.getName() + " put " + amount + " in pot. Pot: " + size + "/" + CAPACITY);

        if(size >= CAPACITY) {
            notifyAll();
        }
    }

    public synchronized int get() throws InterruptedException {
        while(size < CAPACITY) wait();

        int tmp = size;
        size = 0;

        System.out.println("Winni get " + tmp + " from pot. Pot: " + size + "/" + CAPACITY);
        notify();

        return tmp;
    }

    public int getCAPACITY() {
        return CAPACITY;
    }

    public int getSize() {
        return size;
    }
}
