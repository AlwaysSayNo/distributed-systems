package org.grynko.nazar.a.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.Thread.interrupted;

@Component
@AllArgsConstructor
public class MyRunnable implements Runnable {

    private final RecruitersShire shire;
    private BoundedSemaphore semaphore;

    @Override
    public void run() {
        while(!interrupted()) {
            semaphore.take();

            boolean isFinished = false;
            synchronized (shire) {
                isFinished = !shire.unfold();
                //System.out.println(shire);
            }

            if(isFinished) return;

            semaphore.release();
        }
    }

}
