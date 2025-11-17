package com.falae.bff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
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

    private WebClient.RequestHeadersSpec<?> addAuthHeader(WebClient.RequestHeadersSpec<?> request, String token) {
        if (token != null && !token.isBlank()) {
            return request.header(HttpHeaders.AUTHORIZATION, token);
        }
        return request;
    }

    // ========== GET ==========
    public <T> Mono<T> get(String path, String auth, Class<T> clazz) {

        WebClient.RequestHeadersSpec<?> req =
                addAuthHeader(client.get().uri(path), auth);

        return req.retrieve()
                .bodyToMono(clazz)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.error(new RuntimeException(ex.getResponseBodyAsString(), ex)));
    }

    // ========== POST ==========
    public <T> Mono<T> post(String path, String auth, Object body, Class<T> clazz) {

        WebClient.RequestBodySpec req =
                client.post().uri(path).contentType(MediaType.APPLICATION_JSON);

        // aplica auth depois
        WebClient.RequestBodySpec reqWithAuth = (WebClient.RequestBodySpec)
                addAuthHeader(req, auth);

        return reqWithAuth.bodyValue(body == null ? "" : body)
                .retrieve()
                .bodyToMono(clazz)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.error(new RuntimeException(ex.getResponseBodyAsString(), ex)));
    }

    // ========== PUT ==========
    public Mono<Void> put(String path, String auth, Object body) {

        WebClient.RequestBodySpec req =
                client.put().uri(path).contentType(MediaType.APPLICATION_JSON);

        WebClient.RequestBodySpec reqWithAuth = (WebClient.RequestBodySpec)
                addAuthHeader(req, auth);

        return reqWithAuth.bodyValue(body == null ? "" : body)
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.error(new RuntimeException(ex.getResponseBodyAsString(), ex)));
    }

    // ========== PATCH ==========
    public Mono<Void> patch(String path, String auth, Object body) {

        WebClient.RequestBodySpec req =
                client.patch().uri(path).contentType(MediaType.APPLICATION_JSON);

        WebClient.RequestBodySpec reqWithAuth = (WebClient.RequestBodySpec)
                addAuthHeader(req, auth);

        return reqWithAuth.bodyValue(body == null ? "" : body)
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.error(new RuntimeException(ex.getResponseBodyAsString(), ex)));
    }

    // ========== DELETE ==========
    public Mono<Void> delete(String path, String auth) {

        WebClient.RequestHeadersSpec<?> req =
                addAuthHeader(client.delete().uri(path), auth);

        return req.retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.error(new RuntimeException(ex.getResponseBodyAsString(), ex)));
    }
}
