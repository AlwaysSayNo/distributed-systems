package org.grynko.nazar.task_3.server;

import lombok.SneakyThrows;
import org.grynko.nazar.task_3.server.config.ServerConfig;
import org.grynko.nazar.task_3.server.controller.ServerFrontController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerMain {

    @SneakyThrows
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ServerConfig.class);

        ServerFrontController controller =
                context.getBean("serverFrontController", ServerFrontController.class);

        controller.processRequest();

        context.close();
    }

}
