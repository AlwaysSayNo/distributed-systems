package org.grynko.nazar.b.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Configuration
@ComponentScan("org.grynko.nazar.b")
@PropertySource("classpath:b/application.properties")
public class Lab4_B_Configuration {

    @Bean
    public ThreadPoolExecutor executorService(@Value("${pool.size}") Integer poolSize) {
        return (ThreadPoolExecutor) Executors.newFixedThreadPool(poolSize);
    }

    @Bean
    public Path path(@Value("${file.full.name.a}") String fileName) {
        return Paths.get(fileName);
    }

    @Bean
    public ReentrantReadWriteLock lock() {
        return new ReentrantReadWriteLock();
    }

}
