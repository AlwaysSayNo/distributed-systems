package org.grynko.nazar.task_2.server.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Configuration
@ComponentScan("org.grynko.nazar.task_2.server")
@PropertySource("classpath:task_2/application.properties")
public class ServerConfig {

    // Non Jar way
    @Bean
    public URL sqlFolderURL(){
        return getClass().getClassLoader().getResource("task_2/sql");
    }

    @SneakyThrows
    @Bean
    public Registry localRegistry(@Value("${registry.local.port}") Integer port) {
        return LocateRegistry.createRegistry(port);
    }

}