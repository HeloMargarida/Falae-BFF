package com.falae.bff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.falae.bff.dto.FeedbackDtos.FeedbackInput;

import reactor.core.publisher.Mono;

@Service
public class FeedbacksService {

    @Autowired
    private BackendService backend;

    public Mono<Object> listar(String auth) {
        return backend.get("/api/Feedbacks", auth, Object.class);
    }

    public Mono<Object> obter(String id, String auth) {
        return backend.get("/api/Feedbacks/" + id, auth, Object.class);
    }

    public Mono<Object> criar(FeedbackInput input, String auth) {
        return backend.post("/api/Feedbacks", auth, input, Object.class);
    }

    public Mono<Object> atualizar(String id, FeedbackInput input, String auth) {
        return backend.put("/api/Feedbacks/" + id, auth, input)
                .thenReturn("Feedback atualizado");
    }

    public Mono<Object> deletar(String id, String auth) {
        return backend.delete("/api/Feedbacks/" + id, auth)
                .thenReturn("Feedback removido");
    }

    public Mono<Object> porEncontro(String encontroId, String auth) {
        return backend.get("/api/Feedbacks/encontro/" + encontroId, auth, Object.class);
    }

    public Mono<Object> porUsuario(String usuarioId, String auth) {
        return backend.get("/api/Feedbacks/usuario/" + usuarioId, auth, Object.class);
    }

    public Mono<Object> verificar(String encontroId, String usuarioId, String auth) {
        return backend.get("/api/Feedbacks/verificar/" + encontroId + "/" + usuarioId, auth, Object.class);
    }
}
