package org.grynko.nazar.a;

import lombok.SneakyThrows;
import org.grynko.nazar.a.application.MyRunnable;
import org.grynko.nazar.a.application.RecruitersShire;
import org.grynko.nazar.a.config.Lab5_A_Configuration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ThreadPoolExecutor;

public class ShireTask {

    @SneakyThrows
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Lab5_A_Configuration.class);

        ThreadPoolExecutor executor = context.getBean("executorService", ThreadPoolExecutor.class);
        RecruitersShire shire = context.getBean("recruitersShire", RecruitersShire.class);

        for(int i = 0; i < executor.getMaximumPoolSize(); ++i) {
            Runnable task = context.getBean("myRunnable", MyRunnable.class);
            executor.execute(task);
        }

        while(executor.getActiveCount() > 1) {
            System.out.println(shire);
            System.out.println(executor.getActiveCount());
            Thread.sleep(1000);
        }

        executor.shutdownNow();
    }

}
