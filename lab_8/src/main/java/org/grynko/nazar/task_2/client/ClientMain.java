package org.grynko.nazar.task_2.client;

import org.grynko.nazar.task_2.client.cases.Cases;
import org.grynko.nazar.task_2.client.controller.ClientFrontController;
import org.grynko.nazar.task_2.client.config.ClientConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClientMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ClientConfig.class);

        ClientFrontController controller =
                context.getBean("clientFrontController", ClientFrontController.class);

        doOperation(controller);

        context.close();
    }

    private static void doOperation(ClientFrontController controller) {
        Cases.case7(controller);
    }

}
