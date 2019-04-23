package com.sony.spe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Configuration
@EnableAsync
@ComponentScan(value = "com.sony.spe")
public class ExcelUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExcelUploadApplication.class, args);
    }
}