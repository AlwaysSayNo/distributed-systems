package main.org.grynko.nazar.b;

import java.util.Random;

public class HairSalon {

    public static final int VISITORS_AMOUNT = 8;

    public static void main(String[] args) {
        Hairdresser hairdresser = new Hairdresser();

        for(int i = 0; i < VISITORS_AMOUNT; ++i){
            new Thread(new Client(Integer.toString(i), hairdresser)).start();
        }
    }

}
