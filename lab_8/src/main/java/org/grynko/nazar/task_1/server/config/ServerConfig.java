package org.grynko.nazar.task_1.server.config;

import org.grynko.nazar.task_1.server.socket.SingleServerSocketPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.Socket;
import java.net.URL;

@Configuration
@ComponentScan("org.grynko.nazar.task_1.server")
@PropertySource("classpath:task_1/application.properties")
public class ServerConfig {

    @Bean
    public Socket socket(SingleServerSocketPool singleServerSocket) {
        return singleServerSocket.getSocket();
    }

    // Non Jar way
    @Bean
    public URL sqlFolderURL(){
        return getClass().getClassLoader().getResource("task_1/sql");
    }

}
