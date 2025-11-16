package com.falae.bff.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Mono;

@Service
public class BackendService {

    private final WebClient client;

    @Autowired
    public BackendService(@Qualifier("backendWebClient") WebClient backendWebClient) {
        this.client = backendWebClient;
    }

    private WebClient.RequestHeadersSpec<?> addAuthHeader(WebClient.RequestHeadersSpec<?> request, String authHeader) {
        if (authHeader != null && !authHeader.isBlank()) {
            return request.header(HttpHeaders.AUTHORIZATION, authHeader);
        }
        return request;
    }

    // ===============================
    // GET
    // ===============================
    public <T> Mono<T> get(String path, String authHeader, Class<T> clazz) {
        var request = client.get().uri(path);
        request = (WebClient.RequestHeadersSpec<?>) addAuthHeader(request, authHeader);

        return request.retrieve()
                .bodyToMono(clazz)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.error(new RuntimeException(ex.getResponseBodyAsString(), ex)));
    }

    // ===============================
    // POST  (CORRIGIDO)
    // ===============================
    public <T> Mono<T> post(String path, String authHeader, Object body, Class<T> clazz) {

        var request = client.post()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON);

        request = (RequestBodySpec) addAuthHeader(request, authHeader);

        return request
                .bodyValue(body) // <---- CORREÇÃO AQUI
                .retrieve()
                .bodyToMono(clazz)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.error(new RuntimeException(ex.getResponseBodyAsString(), ex)));
    }

    // ===============================
    // PUT (CORRIGIDO)
    // ===============================
    public Mono<Void> put(String path, String authHeader, Object body) {

        var request = client.put()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON);

        request = (RequestBodySpec) addAuthHeader(request, authHeader);

        return request
                .bodyValue(body) // <---- CORRIGIDO
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.error(new RuntimeException(ex.getResponseBodyAsString(), ex)));
    }

    // ===============================
    // PATCH (CORRIGIDO)
    // ===============================
    public Mono<Void> patch(String path, String authHeader, Object body) {

        var request = client.patch()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON);

        request = (RequestBodySpec) addAuthHeader(request, authHeader);

        return request
                .bodyValue(body) // <---- CORRIGIDO
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.error(new RuntimeException(ex.getResponseBodyAsString(), ex)));
    }

    // ===============================
    // DELETE
    // ===============================
    public Mono<Void> delete(String path, String authHeader) {
        var request = client.delete().uri(path);
        request = (WebClient.RequestHeadersSpec<?>) addAuthHeader(request, authHeader);

        return request.retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.error(new RuntimeException(ex.getResponseBodyAsString(), ex)));
    }

    // ===============================
    // GET AS MAP
    // ===============================
    public Mono<Map<String, Object>> getAsMap(String path, String authHeader) {

        var request = client.get().uri(path);
        request = (WebClient.RequestHeadersSpec<?>) addAuthHeader(request, authHeader);

        return request.retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.error(new RuntimeException(ex.getResponseBodyAsString(), ex)));
    }
}
