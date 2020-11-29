package com.pshdhx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * @Authtor pshdhx
 * @Date 2020/11/220:55
 * @Version 1.0
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

    @Bean
    public ViewResolver myviewResolver(){
        return new MyViewResolver();
    }
    private static class MyViewResolver implements  ViewResolver{

        @Override
        public View resolveViewName(String s, Locale locale) throws Exception {
            return null;
        }
    }
}
