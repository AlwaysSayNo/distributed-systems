package org.grynko.nazar.task_3.server.config;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Configuration
@ComponentScan("org.grynko.nazar.task_3.server")
@PropertySource("classpath:task_3/application.properties")
public class ServerConfig {

    @SneakyThrows
    @Bean
    public Channel channel() {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        return connection.createChannel();
    }

}