package com.siatsenko.movieland.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.concurrent.*;

@Configuration
public class ApplicationConfig implements SchedulingConfigurer {

    private Environment environment;
    private DataSource dataSource;

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(Integer.parseInt(environment.getProperty("scheduler.pool-size")));
        taskScheduler.initialize();
        scheduledTaskRegistrar.setTaskScheduler(taskScheduler);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ExecutorService executorService() {
        return new ThreadPoolExecutor(Integer.parseInt(environment.getProperty("threadPool.corePoolSize"))
                , Integer.parseInt(environment.getProperty("threadPool.maxPoolSize"))
                , Integer.parseInt(environment.getProperty("threadPool.keepAliveSec"))
                , TimeUnit.SECONDS
                , new SynchronousQueue<Runnable>());
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
