package org.grynko.nazar.b.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.CyclicBarrier;

@Configuration
@ComponentScan("org.grynko.nazar.b.application")
@PropertySource("classpath:b/application.properties")
public class Lab5_B_Configuration {

    @Bean
    public CyclicBarrier barrier(@Value("${thread.amount}") int threadAmount) {
        return new CyclicBarrier(threadAmount);
    }

}
