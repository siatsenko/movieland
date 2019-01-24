package com.siatsenko.movieland.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.siatsenko.movieland"
  , excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.siatsenko.movieland.controller.*"))
//@ImportResource({"classpath*:spring/root-context.xml"})
@EnableWebMvc
@PropertySource("classpath:application.yml")
public class ApplicationConfig implements SchedulingConfigurer
//        , WebApplicationInitializer
{
    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    @Autowired
    private Environment environment;

    @Autowired
    private DataSource dataSource;

    //@Bean
//@Scope("singleton")
//    public BasicDataSource dataSource() {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(environment.getProperty("dbcp2.driverClassName"));
//        dataSource.setUrl(environment.getProperty("dbcp2.url"));
//        dataSource.setUsername(environment.getProperty("dbcp2.username"));
//        dataSource.setPassword(environment.getProperty("dbcp2.password"));
//        dataSource.setDefaultAutoCommit(Boolean.valueOf(environment.getProperty("dbcp2.defaultAutoCommit")));
//        dataSource.setMinIdle(Integer.parseInt(environment.getProperty("dbcp2.minIdle")));
//        dataSource.setMaxIdle(Integer.parseInt(environment.getProperty("dbcp2.maxIdle")));
//        dataSource.setMaxOpenPreparedStatements(Integer.parseInt(environment.getProperty("dbcp2.maxOpenPreparedStatements")));
//        return dataSource;
//    }
/*
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.dbcp2.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.dbcp2.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.dbcp2.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.dbcp2.password"));
        dataSource.setDefaultAutoCommit(Boolean.valueOf(environment.getProperty("spring.datasource.dbcp2.defaultAutoCommit")));
        dataSource.setMinIdle(Integer.parseInt(environment.getProperty("spring.datasource.dbcp2.minIdle")));
        dataSource.setMaxIdle(Integer.parseInt(environment.getProperty("spring.datasource.dbcp2.maxIdle")));
        dataSource.setMaxOpenPreparedStatements(Integer.parseInt(environment.getProperty("spring.datasource.dbcp2.maxOpenPreparedStatements")));
        return dataSource;
    }
*/

//    @Bean
//    public BasicDataSource dataSource() {
//        return this.dataSource;
//    }

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


//    @Bean
//    public static PropertySourcesPlaceholderConfigurer properties() throws IOException {
//        logger.info("Loading configuration.");
//
//        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
//        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
//
//        Resource yamlResource = new ClassPathResource("application.yml");
//
//        logger.info("\tConfiguration loaded from {}", yamlResource.getURL().getPath());
//
//        yamlPropertiesFactoryBean.setResources(yamlResource);
//
//
//        propertySourcesPlaceholderConfigurer.setProperties(yamlPropertiesFactoryBean.getObject());
//        logger.info("Loading configuration. DONE");
//        return propertySourcesPlaceholderConfigurer;
//    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


//    @Override
//    public void onStartup(ServletContext container) throws ServletException {
//// Creates the dispatcher servlet context
//        AnnotationConfigWebApplicationContext servletContext =
//                new AnnotationConfigWebApplicationContext();
//
//// Registers the servlet configuraton with the dispatcher servlet context
//        servletContext.register(AppConfiguration.class);
//
//// Further configures the servlet context
//        ServletRegistration.Dynamic dispatcher =
//                container.addServlet("spring-mvc-dispatcher",
//                        new DispatcherServlet(servletContext));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/v1/*");
//    }

}
