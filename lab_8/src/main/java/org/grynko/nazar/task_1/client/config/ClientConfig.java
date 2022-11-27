package org.grynko.nazar.task_1.client.config;

import org.grynko.nazar.task_1.client.socket.SingleSocketPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.Socket;

@Configuration
@ComponentScan("org.grynko.nazar.task_1.client")
@PropertySource("classpath:task_1/application.properties")
public class ClientConfig {

    // strange name
    @Bean
    public Socket socket(SingleSocketPool singleSocketPool) {
        return singleSocketPool.getSocket();
    }

}
