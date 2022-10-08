package main.org.grynko.nazar.a;

public class PoohAndHoney {

    public static final int CAPACITY = 40;
    public static final int BEE_AMOUNT = 8;

    public static void main(String[] args) {
        Pot pot = new Pot(CAPACITY);

        new Thread(new WinniThePooh(pot)).start();
        for(int i = 1; i <= BEE_AMOUNT; ++i) new Thread(new Bee(Integer.toString(i), pot)).start();

    }

}
