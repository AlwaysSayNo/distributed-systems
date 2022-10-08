package org.grynko.nazar.b.application.runnable;

import lombok.AllArgsConstructor;
import org.grynko.nazar.b.application.CustomRunnable;
import org.grynko.nazar.b.application.Garden;
import org.grynko.nazar.b.application.PlantState;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Thread.interrupted;

@Component
@AllArgsConstructor
public class NatureRunnable implements Runnable, CustomRunnable {

    private final Garden garden;
    private final ReentrantReadWriteLock lock;

    @Override
    public void run() {
        Random random = new Random();

        while (!interrupted()) {
            lock.writeLock().lock();

            int randomWidth = random.nextInt(garden.getWidth());
            int randomHeight = random.nextInt(garden.getHeight());

            System.out.printf("==> Nature randomly changes the cell (%d, %d):%n\n",
                    randomWidth, randomHeight);

            PlantState state = garden.getCell(randomWidth, randomHeight);
            if (state == PlantState.WATERED) state = PlantState.DRY;
            else state = PlantState.WATERED;

            garden.setCell(randomWidth, randomHeight, state);

            lock.writeLock().unlock();

            justSleep();
        }

    }

}
