package com.falae.bff.controller;

import com.falae.bff.dto.EncontrosDtos.MatchingInput;
import com.falae.bff.service.BackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bff/encontros")
public class EncontrosBffController {

    @Autowired
    private BackendService backend;

    /* ----------------------------
       GET: /bff/encontros
     ---------------------------- */
    @GetMapping
    public ResponseEntity<Object> getEncontros(@RequestHeader("Authorization") String auth) {

        Object result = backend
                .get("/api/encontros", auth, Object.class)
                .block();

        return ResponseEntity.ok(result);
    }

    /* ----------------------------
       GET: /bff/encontros/{id}
     ---------------------------- */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getEncontro(
            @PathVariable String id,
            @RequestHeader("Authorization") String auth
    ) {

        Object result = backend
                .get("/api/encontros/" + id, auth, Object.class)
                .block();

        return ResponseEntity.ok(result);
    }

    /* ----------------------------
       POST: /bff/encontros/matching
     ---------------------------- */
    @PostMapping("/matching")
    public ResponseEntity<Object> matching(
            @RequestBody MatchingInput req,
            @RequestHeader("Authorization") String authHeader
    ) {

        Object result = backend
                .post("/api/encontros/matching", authHeader, req, Object.class)
                .block();

        return ResponseEntity.ok(result);
    }

    /* ----------------------------
       POST: /bff/encontros
       (caso você queira criar encontros pelo BFF)
     ---------------------------- */
    @PostMapping
    public ResponseEntity<Object> criarEncontro(
            @RequestBody Object req,
            @RequestHeader("Authorization") String auth
    ) {

        Object result = backend
                .post("/api/encontros", auth, req, Object.class)
                .block();

        return ResponseEntity.ok(result);
    }
}
