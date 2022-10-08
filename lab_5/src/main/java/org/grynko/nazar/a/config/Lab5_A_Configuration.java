package org.grynko.nazar.a.config;

import org.grynko.nazar.a.application.BoundedSemaphore;
import org.grynko.nazar.a.application.RecruitersShire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@ComponentScan("org.grynko.nazar.a.application")
@PropertySource("classpath:a/application.properties")
public class Lab5_A_Configuration {

    @Bean
    public BoundedSemaphore boundedSemaphore(@Value("${soldiers.amount}") int soldiersAmount,
                                 @Value("${soldiers.per.thread}") int soldiersPerThread) {
        return new BoundedSemaphore(soldiersAmount / soldiersPerThread);
    }

    @Bean
    public RecruitersShire recruitersShire(@Value("${soldiers.amount}") int soldiersAmount) {
        return new RecruitersShire(soldiersAmount);
    }

    @Bean
    public ThreadPoolExecutor executorService(@Value("${pool.size}") Integer poolSize) {
        return (ThreadPoolExecutor) Executors.newFixedThreadPool(poolSize);
    }

}
