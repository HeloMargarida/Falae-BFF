package com.falae.bff.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class EndpointLogger implements ApplicationListener<ApplicationReadyEvent> {

    private final RequestMappingHandlerMapping handlerMapping;

    public EndpointLogger(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("=== ENDPOINTS REGISTRADOS ===");
        handlerMapping.getHandlerMethods().forEach((key, value) -> {
            System.out.println(key + " -> " + value);
        });
        System.out.println("==============================");
    }
}
