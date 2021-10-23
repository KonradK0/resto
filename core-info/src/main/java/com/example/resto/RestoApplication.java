package com.example.resto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RestoApplication {

    private static final Logger log = LoggerFactory.getLogger(RestoApplication.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(RestoApplication.class);

        for (String bean : ctx.getBeanDefinitionNames()) {
            log.debug(bean);
        }
    }
}
