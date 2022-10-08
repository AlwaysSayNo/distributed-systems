package org.grynko.nazar.b.application;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public interface CustomRunnable {

    @SneakyThrows
    default void justSleep() {
        TimeUnit.SECONDS.sleep(2);
    }

}
