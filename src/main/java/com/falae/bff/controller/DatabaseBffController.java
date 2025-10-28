package com.falae.bff.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.falae.bff.service.BackendService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bff/database")
public class DatabaseBffController {

    @Autowired
    private BackendService backend;

    @GetMapping("/tables")
    public Mono<ResponseEntity<Object>> tables(
            @RequestHeader(value = "Authorization", required = false) String auth) {
                
        return backend.get("/api/Database/tables", auth, Object.class)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/create-tables")
    public Mono<ResponseEntity<Object>> createTables(
            @RequestHeader(value = "Authorization", required = false) String auth) {
        // Envie {} (JSON vazio) em vez de "" para evitar 415 no backend .NET
        return backend.post("/api/Test/create-tables", auth, new HashMap<>(), Object.class)
                .map(ResponseEntity::ok);
    }
}
