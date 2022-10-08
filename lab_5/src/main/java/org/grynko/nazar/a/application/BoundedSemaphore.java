package org.grynko.nazar.a.application;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class BoundedSemaphore {

    private int signals = 0;
    private final int bound;

    public BoundedSemaphore(int bound) {
        this.bound = bound;
    }

    @SneakyThrows
    public synchronized void take() {
        while(this.signals == bound) wait();
        this.signals++;
        notifyAll();
    }

    @SneakyThrows
    public synchronized void release() {
        while(this.signals == 0) wait();
        this.signals--;
        notifyAll();
    }

}
