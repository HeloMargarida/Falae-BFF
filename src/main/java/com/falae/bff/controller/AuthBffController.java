package com.falae.bff.controller;

import com.falae.bff.dto.AuthDtos.LoginRequest;
import com.falae.bff.dto.AuthDtos.RecuperarSenhaRequest;
import com.falae.bff.service.BackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bff/auth")
public class AuthBffController {

    @Autowired
    private BackendService backend;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest req) {

        Object body = backend.post("/api/auth/login", null, req, Object.class).block();

        return ResponseEntity.ok(body);
    }

    @PostMapping("/recuperar-senha")
    public ResponseEntity<Object> recuperar(@RequestBody RecuperarSenhaRequest req) {

        Object result = backend.post("/api/auth/recuperar-senha", null, req, Object.class).block();

        return ResponseEntity.ok(result);
    }
}
