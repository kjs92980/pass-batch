package com.zisu.pass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PassBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(PassBatchApplication.class, args);
    }

}
