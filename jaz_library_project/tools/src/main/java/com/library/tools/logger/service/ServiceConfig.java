package com.library.tools.logger.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@ComponentScan
@Configuration
public class ServiceConfig {
    @Bean
    File createFile(){
        return new File("web_service.log");
    }
}
