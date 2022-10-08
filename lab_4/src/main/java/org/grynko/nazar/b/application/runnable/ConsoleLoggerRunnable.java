package org.grynko.nazar.b.application.runnable;

import lombok.AllArgsConstructor;
import org.grynko.nazar.b.application.CustomRunnable;
import org.grynko.nazar.b.application.Garden;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Thread.interrupted;

@Component
@AllArgsConstructor
public class ConsoleLoggerRunnable implements Runnable, CustomRunnable {

    private final Garden garden;
    private final ReentrantReadWriteLock lock;

    @Override
    public void run() {

        while (!interrupted()) {
            lock.readLock().lock();

            System.out.printf("==> Console displays the log:\n%s\n\n", garden);

            lock.readLock().unlock();

            justSleep();
        }

    }

}
