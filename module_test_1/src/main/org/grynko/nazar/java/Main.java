package main.org.grynko.nazar.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        List<BusStop> busStops = new ArrayList<>();
        Random random = new Random();
        int stopNumber = 5;
        int busNumber = 7;
        int busStopBorder = 5;

        for(int i = 0; i < stopNumber; ++i) {
            int randomBorder = random.nextInt(busStopBorder) + 1;
            String name = String.format("Stop-%d", i + 1);
            BusStop busStop = new BusStop(randomBorder, name);

            busStops.add(busStop);
        }

        for(int i = 0; i < busNumber; ++i) {
            Bus bus = new Bus(busStops);
            String name = String.format("Bus-%d", i + 1);

            new Thread(bus, name).start();
        }
    }

}
