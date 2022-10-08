package org.grynko.nazar.b.application.runnable;

import lombok.AllArgsConstructor;
import org.grynko.nazar.b.application.CustomRunnable;
import org.grynko.nazar.b.application.Garden;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Thread.interrupted;

@Component
@AllArgsConstructor
public class FileLoggerRunnable implements Runnable, CustomRunnable {

    private final Garden garden;
    private final ReentrantReadWriteLock lock;
    private final Path path;

    @Override
    public void run() {

        while(!interrupted()) {
            lock.writeLock().lock();

            try{
                System.out.println("==> Output the state of the garden to a file.\n");

                String message = garden.toString() + "\n\n";
                Files.write(path, message.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println("Something went wrong while work with file.");
            } finally {
                lock.writeLock().unlock();
            }

            justSleep();
        }

    }

}
