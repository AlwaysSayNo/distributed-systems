package org.grynko.nazar.task_2.client.config;

import lombok.SneakyThrows;
import org.grynko.nazar.task_2.server.controller.ServerFrontController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.rmi.Naming;

@Configuration
@ComponentScan("org.grynko.nazar.task_2.client")
@PropertySource("classpath:task_2/application.properties")
public class ClientConfig {

    @SneakyThrows
    @Bean
    public ServerFrontController serverFrontController(@Value("${registry.local.host}") String host,
                                                       @Value("${registry.local.port}") Integer port,
                                                       @Value("${server.front.controller.name}") String serverControllerName) {
        String url = String.format("//%s:%d/%s", host, port, serverControllerName);
        return (ServerFrontController) Naming.lookup(url);
    }

}
