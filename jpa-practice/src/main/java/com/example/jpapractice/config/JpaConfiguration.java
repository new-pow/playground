package com.example.jpapractice.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfiguration {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
