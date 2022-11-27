package org.grynko.nazar.task_2.server;

import lombok.SneakyThrows;
import org.grynko.nazar.task_2.server.config.ServerConfig;
import org.grynko.nazar.task_2.server.controller.ServerFrontController;
import org.grynko.nazar.task_2.server.controller.ServerFrontControllerImpl;
import org.grynko.nazar.task_2.server.registry.RegistryWrapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerMain {

    @SneakyThrows
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ServerConfig.class);

        registryObjects(context);

        Thread.sleep(5_000);

        context.close();

        System.exit(0);
    }

    @SneakyThrows
    public static void registryObjects(AnnotationConfigApplicationContext context) {
        RegistryWrapper registry =
                context.getBean("registryWrapper", RegistryWrapper.class);
        ServerFrontController controller =
                context.getBean("serverFrontControllerImpl", ServerFrontControllerImpl.class);

        String serverFrontControllerName = context.getEnvironment().getProperty("server.front.controller.name");
        registry.register(serverFrontControllerName, controller);
    }

}
