package com.hongha.ver1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.hongha.ver1.utils.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditorAwareConfig {
    @Bean
    AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
}