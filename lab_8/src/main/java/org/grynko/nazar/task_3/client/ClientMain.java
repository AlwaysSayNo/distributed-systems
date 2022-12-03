package org.grynko.nazar.task_3.client;

import org.grynko.nazar.task_3.client.cases.Cases;
import org.grynko.nazar.task_3.client.config.ClientConfig;
import org.grynko.nazar.task_3.client.controller.ClientFrontController;
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
        Cases.case2(controller);
    }

}
