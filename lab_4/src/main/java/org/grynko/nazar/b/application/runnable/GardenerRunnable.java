package org.grynko.nazar.b.application.runnable;

import lombok.AllArgsConstructor;
import org.grynko.nazar.b.application.CustomRunnable;
import org.grynko.nazar.b.application.Garden;
import org.grynko.nazar.b.application.PlantState;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Thread.interrupted;

@Component
@AllArgsConstructor
public class GardenerRunnable implements Runnable, CustomRunnable {

    private final Garden garden;
    private final ReentrantReadWriteLock lock;

    @Override
    public void run() {

        while (!interrupted()) {
            lock.writeLock().lock();

            int count = 0;
            for(int i = 0; i < garden.getWidth(); ++i) {
                for(int j = 0; j < garden.getHeight(); ++j) {
                    if(garden.getCell(i, j) == PlantState.DRY) {
                        count++;
                        garden.setCell(i, j, PlantState.WATERED);
                    }
                }
            }

            System.out.printf("==> Gardener is watering %d cells in the garden.\n", count);

            lock.writeLock().unlock();

            justSleep();
        }

    }

}
