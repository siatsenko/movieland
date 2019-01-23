package com.siatsenko.movieland;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
//@ImportResource({"classpath*:/spring/root-context.xml"})
//public class Application  extends SpringBootServletInitializer {
 public class Application {

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        servletContext.setInitParameter("p-name", "p-value");
//        super.onStartup(servletContext);
//    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class,args);

//        for (String name : applicationContext.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }

    }




}
