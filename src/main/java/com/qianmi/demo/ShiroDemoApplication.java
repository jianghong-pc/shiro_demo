package com.qianmi.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.net.UnknownHostException;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan(value = "com.qianmi.demo.dao.mapper")
public class ShiroDemoApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ShiroDemoApplication.class);
    }

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(ShiroDemoApplication.class, args);
    }
}