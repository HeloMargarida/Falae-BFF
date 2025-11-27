package com.falae.bff.service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Service
public class BackendService {

    private final WebClient webClient;

    // Ajuste no application.properties:
    // backend.base-url=https://localhost:7089   (recomendado, já que o .NET redireciona p/ HTTPS)
    // ou, se quiser ficar em HTTP, desative UseHttpsRedirection no backend e use http://localhost:5245
    @Value("${backend.base-url:http://localhost:5000}")
    private String backendBaseUrl;

    public BackendService(WebClient.Builder builder) {
        // HttpClient com followRedirect + SSL "inseguro" apenas para DEV (cert de localhost)
        HttpClient httpClient = HttpClient.create()
            .followRedirect(true)
            .secure(ssl -> ssl.sslContext(
                SslContextBuilder.forClient()
                    // ⚠️ DEV APENAS: aceita qualquer certificado (útil para https://localhost)
                    .trustManager(InsecureTrustManagerFactory.INSTANCE)
            ));

        this.webClient = builder
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeaders(h -> h.setAcceptCharset(java.util.List.of(StandardCharsets.UTF_8)))
            .build();
    }

    /* =========================================================
       Helpers
    ========================================================= */

    private WebClient.RequestHeadersSpec<?> withAuth(WebClient.RequestHeadersSpec<?> spec, String auth) {
        if (auth != null && !auth.isBlank()) {
            String header = auth.startsWith("Bearer ") ? auth : "Bearer " + auth;
            spec.header(HttpHeaders.AUTHORIZATION, header);
        }
        // reforça Accept por requisição (opcional)
        spec.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return spec;
    }

    private <T> Mono<T> handle(WebClient.ResponseSpec resp, Class<T> clazz) {
        return resp
            .onStatus(HttpStatusCode::isError, r -> r.createException().flatMap(Mono::error))
            .bodyToMono(clazz);
    }

    private Mono<Void> handleVoid(WebClient.ResponseSpec resp) {
        return resp
            .onStatus(HttpStatusCode::isError, r -> r.createException().flatMap(Mono::error))
            .bodyToMono(Void.class)
            .then();
    }

    /* =========================================================
       GET
    ========================================================= */

    public <T> Mono<T> get(String path, String auth, Class<T> clazz) {
        var resp = withAuth(
            webClient.get().uri(backendBaseUrl + path),
            auth
        ).retrieve();
        return handle(resp, clazz);
    }

    public Mono<String> get(String path, String auth) {
        var resp = withAuth(
            webClient.get().uri(backendBaseUrl + path),
            auth
        ).retrieve();
        return handle(resp, String.class);
    }

    /* =========================================================
       POST
    ========================================================= */

    public <B, T> Mono<T> post(String path, String auth, B body, Class<T> clazz) {
        var resp = withAuth(
            webClient.post().uri(backendBaseUrl + path)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body == null ? Map.of() : body),
            auth
        ).retrieve();
        return handle(resp, clazz);
    }

    public <B> Mono<String> post(String path, String auth, B body) {
        var resp = withAuth(
            webClient.post().uri(backendBaseUrl + path)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body == null ? Map.of() : body),
            auth
        ).retrieve();
        return handle(resp, String.class);
    }

    /* =========================================================
       PUT
    ========================================================= */

    public <B> Mono<Void> put(String path, String auth, B body) {
        var resp = withAuth(
            webClient.put().uri(backendBaseUrl + path)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body == null ? Map.of() : body),
            auth
        ).retrieve();
        return handleVoid(resp);
    }

    public <B, T> Mono<T> put(String path, String auth, B body, Class<T> clazz) {
        var resp = withAuth(
            webClient.put().uri(backendBaseUrl + path)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body == null ? Map.of() : body),
            auth
        ).retrieve();
        return handle(resp, clazz);
    }

    /* =========================================================
       PATCH
    ========================================================= */

    public <B> Mono<Void> patch(String path, String auth, B body) {
        var resp = withAuth(
            webClient.patch().uri(backendBaseUrl + path)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body == null ? Map.of() : body),
            auth
        ).retrieve();
        return handleVoid(resp);
    }

    public <B, T> Mono<T> patch(String path, String auth, B body, Class<T> clazz) {
        var resp = withAuth(
            webClient.patch().uri(backendBaseUrl + path)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body == null ? Map.of() : body),
            auth
        ).retrieve();
        return handle(resp, clazz);
    }

    /* =========================================================
       DELETE
    ========================================================= */

    public Mono<Void> delete(String path, String auth) {
        var resp = withAuth(
            webClient.delete().uri(backendBaseUrl + path),
            auth
        ).retrieve();
        return handleVoid(resp);
    }

    public <T> Mono<T> delete(String path, String auth, Class<T> clazz) {
        var resp = withAuth(
            webClient.delete().uri(backendBaseUrl + path),
            auth
        ).retrieve();
        return handle(resp, clazz);
    }
}
