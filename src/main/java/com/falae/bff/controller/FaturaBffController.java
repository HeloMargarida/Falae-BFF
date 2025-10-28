package com.falae.bff.controller;

import com.falae.bff.service.BackendService;
import com.falae.bff.util.TokenUtil;

import jakarta.servlet.http.HttpServletRequest;   // ✅ IMPORT CORRETO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bff/faturas")
public class FaturaBffController {

    @Autowired
    private BackendService backend;

    @Autowired
    private TokenUtil tokenUtil;

    @GetMapping
    public ResponseEntity<Object> list(HttpServletRequest request) {
        String auth = tokenUtil.extractAuthHeader(request);
        // BackendService retorna Mono<T>; em MVC usamos .block()
        Object body = backend.get("/api/Fatura", auth, Object.class).block();
        return ResponseEntity.ok(body);
    }
}
