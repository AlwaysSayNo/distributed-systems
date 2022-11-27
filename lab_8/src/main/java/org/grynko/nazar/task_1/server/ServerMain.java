package org.grynko.nazar.task_1.server;

import org.grynko.nazar.task_1.server.config.ServerConfig;
import org.grynko.nazar.task_1.server.controller.ServerFrontController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerMain {

    public static void main(String[] args) {
        System.out.println("Start server main");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ServerConfig.class);

        ServerFrontController controller =
                context.getBean("serverFrontController", ServerFrontController.class);

        try {
            while (true){
                controller.process();
            }
        }
        catch (Exception e) {
            //System.out.println("ServerMain#Exception: " + e);
            //e.printStackTrace();
        }

        context.close();
    }

}
