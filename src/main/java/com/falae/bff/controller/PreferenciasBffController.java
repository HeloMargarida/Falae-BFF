package com.falae.bff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.falae.bff.dto.PreferenciasDtos.PreferenciasInput;
import com.falae.bff.service.BackendService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bff/preferencias")
public class PreferenciasBffController {

    @Autowired
    private BackendService backend;

    @GetMapping
    public Mono<ResponseEntity<Object>> list(
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.get("/api/Preferencias", auth, Object.class).map(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Object>> get(
            @PathVariable String id,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.get("/api/Preferencias/" + id, auth, Object.class).map(ResponseEntity::ok);
    }

    @GetMapping("/usuario/{usuarioId}")
    public Mono<ResponseEntity<Object>> byUsuario(
            @PathVariable String usuarioId,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.get("/api/Preferencias/usuario/" + usuarioId, auth, Object.class).map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<Object>> create(
            @RequestBody PreferenciasInput input,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.post("/api/Preferencias", auth, input, Object.class).map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> update(
            @PathVariable String id,
            @RequestBody PreferenciasInput input,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.put("/api/Preferencias/" + id, auth, input).map(v -> ResponseEntity.noContent().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable String id,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.delete("/api/Preferencias/" + id, auth).map(v -> ResponseEntity.noContent().build());
    }

    @DeleteMapping("/usuario/{usuarioId}")
    public Mono<ResponseEntity<Void>> deleteByUsuario(
            @PathVariable String usuarioId,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.delete("/api/Preferencias/usuario/" + usuarioId, auth).map(v -> ResponseEntity.noContent().build());
    }
}
