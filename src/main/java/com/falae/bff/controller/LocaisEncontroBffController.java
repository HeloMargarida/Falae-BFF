package com.falae.bff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.falae.bff.dto.LocalEncontroDtos.LocalEncontroInput;
import com.falae.bff.service.BackendService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bff/locais")
public class LocaisEncontroBffController {

    @Autowired
    private BackendService backend;

    @GetMapping
    public Mono<ResponseEntity<Object>> list(
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.get("/api/LocaisEncontro", auth, Object.class).map(ResponseEntity::ok);
    }

    @GetMapping("/ativos")
    public Mono<ResponseEntity<Object>> ativos(
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.get("/api/LocaisEncontro/ativos", auth, Object.class).map(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Object>> get(
            @PathVariable String id,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.get("/api/LocaisEncontro/" + id, auth, Object.class).map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<Object>> create(
            @RequestBody LocalEncontroInput input,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.post("/api/LocaisEncontro", auth, input, Object.class).map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Void>> update(
            @PathVariable String id,
            @RequestBody LocalEncontroInput input,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.put("/api/LocaisEncontro/" + id, auth, input).map(v -> ResponseEntity.noContent().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(
            @PathVariable String id,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.delete("/api/LocaisEncontro/" + id, auth).map(v -> ResponseEntity.noContent().build());
    }

    @PatchMapping("/{id}/ativar")
    public Mono<ResponseEntity<Void>> ativar(
            @PathVariable String id,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.patch("/api/LocaisEncontro/" + id + "/ativar", auth, "")
                .map(v -> ResponseEntity.noContent().build());
    }

    @PatchMapping("/{id}/desativar")
    public Mono<ResponseEntity<Void>> desativar(
            @PathVariable String id,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.patch("/api/LocaisEncontro/" + id + "/desativar", auth, "")
                .map(v -> ResponseEntity.noContent().build());
    }

    @GetMapping("/{id}/encontros")
    public Mono<ResponseEntity<Object>> encontrosPorLocal(
            @PathVariable String id,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return backend.get("/api/LocaisEncontro/" + id + "/encontros", auth, Object.class).map(ResponseEntity::ok);
    }
}
