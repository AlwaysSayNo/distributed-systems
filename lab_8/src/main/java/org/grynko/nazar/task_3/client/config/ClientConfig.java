package org.grynko.nazar.task_3.client.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.grynko.nazar.task_3.client")
@PropertySource("classpath:task_3/application.properties")
public class ClientConfig {

    @SneakyThrows
    @Bean
    public Channel channel() {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        return connection.createChannel();
    }

}
