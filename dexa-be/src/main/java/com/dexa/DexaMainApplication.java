package com.dexa;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@EnableJpaRepositories
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "DEXA Application OpenAPI", version = "v1.0", description = "DEXA Application OpenAPI"))
public class DexaMainApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DexaMainApplication.class);
    }

    public static void main(String[] args) {
        System.out.println("----- APPLICATION INITIALIZED -----");
        SpringApplication.run(DexaMainApplication.class, args);
    }

    @PostConstruct
    public void postConstructApplication() {
        System.out.println("----- POST CONSTRUCT APPLICATION DONE -----");
    }

}