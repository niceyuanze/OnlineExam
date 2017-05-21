package com.onlineExam.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * Created by niceyuanze on 16-11-29.
 */
@Configuration
@ComponentScan("com.onlineExam")
public class HibernateConfig {

    @Bean
    public org.hibernate.cfg.Configuration getConfiguration(){
        return new org.hibernate.cfg.Configuration().configure();
    }

    @Bean
    public ServiceRegistry getServiceRegistry(@Autowired org.hibernate.cfg.Configuration configuration){
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return serviceRegistry;
    }
    @Bean
    public SessionFactory getSessionFactory(@Autowired ServiceRegistry serviceRegistry,
                                            @Autowired org.hibernate.cfg.Configuration configuration){
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;

    }
}
