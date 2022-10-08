package org.grynko.nazar.b;

import org.grynko.nazar.b.application.runnable.ConsoleLoggerRunnable;
import org.grynko.nazar.b.application.runnable.FileLoggerRunnable;
import org.grynko.nazar.b.application.runnable.GardenerRunnable;
import org.grynko.nazar.b.application.runnable.NatureRunnable;
import org.grynko.nazar.b.config.Lab4_B_Configuration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ThreadPoolExecutor;

public class GardenApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Lab4_B_Configuration.class);

        FileLoggerRunnable fileLoggerRunnable = context.getBean("fileLoggerRunnable", FileLoggerRunnable.class);
        ConsoleLoggerRunnable consoleLoggerRunnable = context.getBean("consoleLoggerRunnable", ConsoleLoggerRunnable.class);
        GardenerRunnable gardenerRunnable = context.getBean("gardenerRunnable", GardenerRunnable.class);
        NatureRunnable natureRunnable = context.getBean("natureRunnable", NatureRunnable.class);

        Thread fileLogger = new Thread(fileLoggerRunnable);
        fileLogger.setPriority(1);
        fileLogger.start();

        Thread consoleLogger = new Thread(consoleLoggerRunnable);
        consoleLogger.setPriority(1);
        consoleLogger.start();

        Thread gardener = new Thread(gardenerRunnable);
        gardener.setPriority(3 );
        gardener.start();

        Thread nature = new Thread(natureRunnable);
        nature.setPriority(8);
        nature.start();

    }

}
