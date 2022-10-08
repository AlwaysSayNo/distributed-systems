package org.grynko.nazar.a.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@ComponentScan("org.grynko.nazar.a")
@PropertySource("classpath:a/application.properties")
public class Lab4_A_Configuration {

    @Bean
    public ThreadPoolExecutor executorService(@Value("${pool.size}") Integer poolSize) {
        return (ThreadPoolExecutor) Executors.newFixedThreadPool(poolSize);
    }

    @Bean
    public Path path(@Value("${file.full.name.a}") String fileName) {
        return Paths.get(fileName);
    }

}
