package com.falae.bff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.falae.bff.dto.AuthDtos.LoginRequest;
import com.falae.bff.dto.AuthDtos.RecuperarSenhaRequest;
import com.falae.bff.service.BackendService;

@RestController
@RequestMapping("/bff/auth")
public class AuthBffController {

    @Autowired
    private BackendService backend;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest req) {
        // Em MVC usamos .block() para obter a resposta do WebClient
        Object body = backend.post("/api/auth/login", "", req, Object.class).block();
        return ResponseEntity.ok(body);
    }
    

    @PostMapping("/recuperar-senha")
    public ResponseEntity<Object> recuperar(@RequestBody RecuperarSenhaRequest req) {
        Object body = backend.post("/api/auth/recuperar-senha", "", req, Object.class).block();
        return ResponseEntity.ok(body);
    }
}
