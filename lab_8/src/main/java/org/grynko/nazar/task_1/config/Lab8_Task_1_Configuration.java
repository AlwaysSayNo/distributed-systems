package org.grynko.nazar.task_1.config;

import lombok.SneakyThrows;
import org.grynko.nazar.task_1.server.SingleSocketServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.ServerSocket;
import java.net.Socket;

@Configuration
@ComponentScan("org.grynko.nazar.task_1")
@PropertySource("classpath:task_1/application.properties")
public class Lab8_Task_1_Configuration {

    @SneakyThrows
    @Bean
    public ServerSocket serverSocket(SingleSocketServer singleSocketServer) {
        return singleSocketServer.getServer();
    }

    @Bean
    public SingleSocketServer singleSocketServer() {
        return new SingleSocketServer();
    }



}
