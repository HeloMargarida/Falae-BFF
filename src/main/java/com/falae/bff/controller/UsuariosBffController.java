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

import com.falae.bff.dto.UsuarioDtos.UsuarioInput;
import com.falae.bff.service.BackendService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bff/usuarios")
public class UsuariosBffController {

    @Autowired
    private BackendService backend;

    @GetMapping
    public Mono<ResponseEntity<Object>> list(
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.get("/api/Usuarios", auth, Object.class).map(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Object>> getById(
            @PathVariable String id,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.get("/api/Usuarios/" + id, auth, Object.class).map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<Object>> create(
            @RequestBody UsuarioInput input,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.post("/api/Usuarios", auth, input, Object.class).map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> update(
            @PathVariable String id,
            @RequestBody UsuarioInput input,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.put("/api/Usuarios/" + id, auth, input).map(v -> ResponseEntity.noContent().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable String id,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.delete("/api/Usuarios/" + id, auth).map(v -> ResponseEntity.noContent().build());
    }
}
