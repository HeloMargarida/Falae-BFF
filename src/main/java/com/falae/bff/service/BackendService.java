package com.falae.bff.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Mono;

@Service
public class BackendService {

    private final WebClient client;

    @Autowired
    public BackendService(@Qualifier("backendWebClient") WebClient backendWebClient) {
        this.client = backendWebClient;
    }

    public <T> Mono<T> get(String path, String authHeader, Class<T> clazz) {
        return client.get()
                .uri(path)
                .header(HttpHeaders.AUTHORIZATION, authHeader == null ? "" : authHeader)
                .retrieve()
                .bodyToMono(clazz)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.error(new RuntimeException(ex.getResponseBodyAsString(), ex)));
    }

    public <T> Mono<T> post(String path, String authHeader, Object body, Class<T> clazz) {
        return client.post()
                .uri(path)
                .header(HttpHeaders.AUTHORIZATION, authHeader == null ? "" : authHeader)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(clazz)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.error(new RuntimeException(ex.getResponseBodyAsString(), ex)));
    }

    public Mono<Void> put(String path, String authHeader, Object body) {
        return client.put()
                .uri(path)
                .header(HttpHeaders.AUTHORIZATION, authHeader == null ? "" : authHeader)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.error(new RuntimeException(ex.getResponseBodyAsString(), ex)));
    }

    public Mono<Void> patch(String path, String authHeader, Object body) {
        return client.patch()
                .uri(path)
                .header(HttpHeaders.AUTHORIZATION, authHeader == null ? "" : authHeader)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.error(new RuntimeException(ex.getResponseBodyAsString(), ex)));
    }

    public Mono<Void> delete(String path, String authHeader) {
        return client.delete()
                .uri(path)
                .header(HttpHeaders.AUTHORIZATION, authHeader == null ? "" : authHeader)
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.error(new RuntimeException(ex.getResponseBodyAsString(), ex)));
    }

    public Mono<Map<String, Object>> getAsMap(String path, String authHeader) {
        return client.get()
                .uri(path)
                .header(HttpHeaders.AUTHORIZATION, authHeader == null ? "" : authHeader)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.error(new RuntimeException(ex.getResponseBodyAsString(), ex)));
    }
}
