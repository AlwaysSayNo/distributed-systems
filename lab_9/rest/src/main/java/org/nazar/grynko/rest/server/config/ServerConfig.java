package org.nazar.grynko.rest.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.nazar.grynko.rest.server")
@PropertySource("classpath:application.properties")
public class ServerConfig {

}