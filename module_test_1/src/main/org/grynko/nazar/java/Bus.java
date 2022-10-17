package main.org.grynko.nazar.java;

import lombok.SneakyThrows;

import java.util.List;
import java.util.Random;

import static java.lang.Thread.interrupted;

public class Bus implements Runnable {

    private int currentIndex;
    private final List<BusStop> stops;
    private Direction direction;

    public Bus(List<BusStop> stops) {
        this.currentIndex = 0;
        this.stops = stops;
        this.direction = Direction.FORWARD;
    }

    @Override
    public void run() {
        BusStop busStop = stops.get(currentIndex);

        while(!interrupted()) {
            busStop.stop();

            randomSleep();
            busStop = getNextBusStop();
        }
    }

    private BusStop getNextBusStop() {
        currentIndex = nextIndex();
        return stops.get(currentIndex);
    }

    private int nextIndex() {
        int stopAmount = stops.size();
        int nextIndex = currentIndex;

        if(currentIndex == stopAmount - 1) {
            nextIndex--;
            direction = Direction.BACKWARD;
        }
        else if(currentIndex == 0) {
            nextIndex++;
            direction = Direction.FORWARD;
        }
        else {
            switch (direction) {
                case FORWARD: {
                    nextIndex++;
                    break;
                }
                case BACKWARD: {
                    nextIndex--;
                    break;
                }
            }
        }

        return nextIndex;
    }

    @SneakyThrows
    private void randomSleep() {
        Random random = new Random();
        Thread.sleep(random.nextInt(200) + 300);
    }

}
