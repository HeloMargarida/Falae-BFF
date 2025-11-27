package com.falae.bff.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.falae.bff.dto.EncontrosDtos.EncontroInput;
import com.falae.bff.dto.EncontrosDtos.EncontroOutput;
import com.falae.bff.dto.EncontrosDtos.MatchingInput;

import reactor.core.publisher.Mono;

@Service
public class EncontrosService {

    @Autowired
    private BackendService backend;

    private static final String BASE = "/api/encontros";

    // ============================================================
    // LISTAR TODOS
    // ============================================================
    public Mono<Object> listar(String authHeader) {
    return backend.get(BASE, authHeader, EncontroOutput[].class)
            .cast(Object.class)
            .onErrorResume(e -> {
                return Mono.just(new EncontroOutput[0]); // << SEMPRE UMA LISTA
            });
}


    // ============================================================
    // OBTER POR ID
    // ============================================================
    public Mono<Object> obter(String id, String authHeader) {
        return backend.get(BASE + "/" + id, authHeader, EncontroOutput.class)
                .cast(Object.class)
                .onErrorResume(e -> Mono.just((Object) error("Erro ao obter encontro", e)));
    }

    // ============================================================
    // CRIAR ENCONTRO
    // ============================================================
    public Mono<Object> criar(EncontroInput input, String authHeader) {

        if (input == null) {
            return Mono.just((Object) error("O corpo da requisição não pode ser nulo", null));
        }

        if (input.localId == null || input.localId.isBlank()) {
            return Mono.just((Object) error("localId é obrigatório", null));
        }

        return backend.post(BASE, authHeader, input, Object.class)
                .cast(Object.class)
                .onErrorResume(e -> Mono.just((Object) error("Erro ao criar encontro", e)));
    }

    // ============================================================
    // ATUALIZAR ENCONTRO
    // ============================================================
    public Mono<Object> atualizar(String id, EncontroInput input, String authHeader) {
        return backend.put(BASE + "/" + id, authHeader, input)
                .then(Mono.just((Object) ok("Encontro atualizado com sucesso")))
                .onErrorResume(e -> Mono.just((Object) error("Erro ao atualizar encontro", e)));
    }

    // ============================================================
    // DELETAR ENCONTRO
    // ============================================================
    public Mono<Object> deletar(String id, String authHeader) {
        return backend.delete(BASE + "/" + id, authHeader)
                .then(Mono.just((Object) ok("Encontro removido com sucesso")))
                .onErrorResume(e -> Mono.just((Object) error("Erro ao remover encontro", e)));
    }

    // ============================================================
    // MATCHING
    // ============================================================
    public Mono<Object> matching(MatchingInput input, String authHeader) {
        return backend.post(BASE + "/matching", authHeader, input, Object.class)
                .cast(Object.class)
                .onErrorResume(e -> Mono.just((Object) error("Erro ao realizar matching", e)));
    }

    // ============================================================
    // ATUALIZAR STATUS
    // ============================================================
    public Mono<Object> atualizarStatus(String id, String status, String authHeader) {
        String url = BASE + "/" + id + "/status?status=" + status;

        return backend.put(url, authHeader, null)
                .then(Mono.just((Object) ok("Status atualizado com sucesso")))
                .onErrorResume(e -> Mono.just((Object) error("Erro ao atualizar status", e)));
    }

    // ============================================================
    // BUSCAR POR STATUS
    // ============================================================
    public Mono<Object> buscarPorStatus(String status, String authHeader) {
        return backend.get(BASE + "/status/" + status, authHeader, Object.class)
                .cast(Object.class)
                .onErrorResume(e -> Mono.just((Object) error("Erro ao buscar encontros por status", e)));
    }

    // ============================================================
    // ADICIONAR PARTICIPANTE
    // ============================================================
    public Mono<Object> adicionarParticipante(String encontroId, String usuarioId, String authHeader) {
        return backend.post(
                BASE + "/" + encontroId + "/participantes/" + usuarioId,
                authHeader,
                null,
                Object.class
        )
                .cast(Object.class)
                .onErrorResume(e -> Mono.just((Object) error("Erro ao adicionar participante", e)));
    }

    // ============================================================
    // REMOVER PARTICIPANTE
    // ============================================================
    public Mono<Object> removerParticipante(String encontroId, String usuarioId, String authHeader) {
        return backend.delete(
                BASE + "/" + encontroId + "/participantes/" + usuarioId,
                authHeader
        )
                .then(Mono.just((Object) ok("Participante removido com sucesso")))
                .onErrorResume(e -> Mono.just((Object) error("Erro ao remover participante", e)));
    }

    // ============================================================
    // HELPERS
    // ============================================================
    private Map<String, Object> ok(String msg) {
        return Map.of("success", true, "message", msg);
    }

    private Map<String, Object> error(String msg, Throwable e) {
        return Map.of(
                "success", false,
                "error", msg,
                "details", e != null ? e.getMessage() : ""
        );
    }
}
