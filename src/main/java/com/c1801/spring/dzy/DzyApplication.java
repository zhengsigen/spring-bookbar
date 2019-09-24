package com.c1801.spring.dzy;

import com.c1801.spring.dzy.model.Account;
import com.c1801.spring.dzy.model.ValidCode;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@MapperScan("com.c1801.spring.dzy.mapper")
public class DzyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DzyApplication.class, args);
    }

//    @Bean
//    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
//        return factory -> {
//            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
//            factory.addErrorPages(error404Page);
//        };
//    }

    @Bean
    public Map<String, Account> accountMap() {
        return new HashMap<>();
    }

}
