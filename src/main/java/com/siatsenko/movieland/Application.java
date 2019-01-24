package com.siatsenko.movieland;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;

//@Configuration
//@EnableAutoConfiguration
@SpringBootApplication
//@ImportResource({"classpath*:/spring/root-context.xml"})
//public class Application  extends SpringBootServletInitializer {
 public class Application implements CommandLineRunner {


    @Autowired
    private DataSource dataSource;

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        servletContext.setInitParameter("p-name", "p-value");
//        super.onStartup(servletContext);
//    }

    public static void main(String[] args)
    {
        ApplicationContext applicationContext = SpringApplication.run(Application.class,args);
//        for (String name : applicationContext.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Connection Polling datasource : "+ dataSource);
    }
}
