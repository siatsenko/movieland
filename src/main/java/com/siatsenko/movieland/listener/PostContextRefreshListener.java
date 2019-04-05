package com.siatsenko.movieland.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class PostContextRefreshListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ConfigurableBeanFactory factory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext context = contextRefreshedEvent.getApplicationContext();
        String[] names = context.getBeanDefinitionNames();

        for (String name : names) {
            BeanDefinition beanDefinition = factory.getMergedBeanDefinition(name);
            String originalClassName = beanDefinition.getBeanClassName();
            if (originalClassName != null) {
                try {
                    Class<?> originalClass = Class.forName(originalClassName);
                    Method[] methods = originalClass.getDeclaredMethods();
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(PostContextRefresh.class)) {
                            Object bean = context.getBean(name);
                            Method currentMethod = bean.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
                            currentMethod.setAccessible(true);
                            currentMethod.invoke(bean);
                            currentMethod.setAccessible(false);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
