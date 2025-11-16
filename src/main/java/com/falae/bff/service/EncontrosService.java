package com.falae.bff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.falae.bff.dto.EncontrosDtos.EncontroInput;
import com.falae.bff.dto.EncontrosDtos.MatchingInput;

import reactor.core.publisher.Mono;

@Service
public class EncontrosService {

    @Autowired
    private BackendService backend;

    public Mono<Object> listar(String auth) {
        return backend.get("/api/Encontros", auth, Object.class);
    }

    public Mono<Object> obter(String id, String auth) {
        return backend.get("/api/Encontros/" + id, auth, Object.class);
    }

    public Mono<Object> criar(EncontroInput input, String auth) {
        return backend.post("/api/Encontros", auth, input, Object.class);
    }

    public Mono<Object> atualizar(String id, EncontroInput input, String auth) {
        return backend.put("/api/Encontros/" + id, auth, input)
                .thenReturn("Encontro atualizado");
    }

    public Mono<Object> deletar(String id, String auth) {
        return backend.delete("/api/Encontros/" + id, auth)
                .thenReturn("Encontro removido");
    }

    public Mono<Object> matching(MatchingInput input, String auth) {
        return backend.post("/api/Encontros/matching", auth, input, Object.class);
    }

    public Mono<Object> atualizarStatus(String id, String status, String auth) {
        return backend.put("/api/Encontros/" + id + "/status?status=" + status, auth, null)
                .thenReturn("Status atualizado");
    }

    public Mono<Object> buscarPorStatus(String status, String auth) {
        return backend.get("/api/Encontros/status/" + status, auth, Object.class);
    }

    public Mono<Object> adicionarParticipante(String id, String userId, String auth) {
        return backend.post("/api/Encontros/" + id + "/participantes/" + userId, auth, null, Object.class);
    }

    public Mono<Object> removerParticipante(String id, String userId, String auth) {
        return backend.delete("/api/Encontros/" + id + "/participantes/" + userId, auth)
                .thenReturn("Participante removido");
    }
}
