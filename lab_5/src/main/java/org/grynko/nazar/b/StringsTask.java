package org.grynko.nazar.b;

import org.grynko.nazar.b.application.Controller;
import org.grynko.nazar.b.application.MyRunnable;
import org.grynko.nazar.b.config.Lab5_B_Configuration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StringsTask {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Lab5_B_Configuration.class);

        Controller controller = context.getBean("controller", Controller.class);

        for(int i = 0; i < controller.getThreadAmount(); ++i){
            MyRunnable runnable = context.getBean("myRunnable", MyRunnable.class);
            new Thread(runnable).start();
        }
    }

}
