package com.github.io.MateusHCandido.bank_service_10.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
@Component
public class EurekaInstanceConfig implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Você pode realizar ações adicionais aqui, mas não pode alterar o InstanceInfo diretamente
        System.out.println("Application " + appName + " is ready and registered with Eureka.");
    }
}