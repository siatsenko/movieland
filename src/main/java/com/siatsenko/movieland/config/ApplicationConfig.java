package com.siatsenko.movieland.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.concurrent.*;

@EnableMBeanExport
@EnableCaching
@Configuration
public class ApplicationConfig implements SchedulingConfigurer {

    private DataSource dataSource;

    @Value("${scheduler.pool-size:5}")
    private int schedulerPoolSize;

    @Value("${threadPool.corePoolSize:0}")
    private int threadPoolCorePoolSize;

    @Value("${threadPool.maxPoolSize:50}")
    private int threadPoolMaxPoolSize;

    @Value("${threadPool.keepAliveSec:60}")
    private int threadPoolKeepAliveSec;

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
        taskScheduler.setPoolSize(schedulerPoolSize);
        taskScheduler.initialize();
        scheduledTaskRegistrar.setTaskScheduler(taskScheduler);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ExecutorService executorService() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

        threadPoolTaskExecutor.setCorePoolSize(threadPoolCorePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(threadPoolMaxPoolSize);
        threadPoolTaskExecutor.setKeepAliveSeconds(threadPoolKeepAliveSec);
        threadPoolTaskExecutor.initialize();

        return threadPoolTaskExecutor.getThreadPoolExecutor();
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
