package com.falae.bff.config;

import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class EndpointLogger implements ApplicationListener<ApplicationReadyEvent> {

    private final List<RequestMappingHandlerMapping> handlerMappings;

    public EndpointLogger(List<RequestMappingHandlerMapping> handlerMappings) {
        this.handlerMappings = handlerMappings;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("=== ENDPOINTS REGISTRADOS ===");
        for (RequestMappingHandlerMapping handlerMapping : handlerMappings) {
            handlerMapping.getHandlerMethods().forEach((key, value) -> {
                System.out.println(key + " -> " + value);
            });
        }
        System.out.println("================================");
    }
}
