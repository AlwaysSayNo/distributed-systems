package org.grynko.nazar.a;

import org.grynko.nazar.a.application.User;
import org.grynko.nazar.a.config.Lab4_A_Configuration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ThreadPoolExecutor;

public class ReadersAndWriters {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Lab4_A_Configuration.class);

        ThreadPoolExecutor executorService = context.getBean("executorService", ThreadPoolExecutor.class);
        for(int i = 0; i < executorService.getMaximumPoolSize(); ++i) {
            Runnable user = context.getBean("user", User.class);
            executorService.execute(user);
        }
    }

}
