package org.grynko.nazar.task_1.config;

import lombok.SneakyThrows;
import org.grynko.nazar.task_1.server.SingleServerSocketPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.ServerSocket;

@Configuration
@ComponentScan("org.grynko.nazar.task_1")
@PropertySource("classpath:task_1/application.properties")
public class Lab8_Task_1_Configuration {

    @SneakyThrows
    @Bean
    public ServerSocket serverSocket(SingleServerSocketPool singleServerSocket) {
        return singleServerSocket.getServer();
    }

    @Bean
    public SingleServerSocketPool singleServerSocket() {
        return new SingleServerSocketPool();
    }



}
