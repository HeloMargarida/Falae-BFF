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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.falae.bff.dto.EncontrosDtos.EncontroInput;
import com.falae.bff.dto.EncontrosDtos.MatchingInput;
import com.falae.bff.service.EncontrosService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bff/encontros")
public class EncontrosBffController {

    @Autowired
    private EncontrosService service;

    // LISTAR TODOS
    @GetMapping
    public Mono<ResponseEntity<Object>> list(
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return service.listar(auth).map(ResponseEntity::ok);
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Object>> get(
            @PathVariable String id,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return service.obter(id, auth).map(ResponseEntity::ok);
    }

    // CRIAR ENCONTRO
    @PostMapping
    public Mono<ResponseEntity<Object>> create(
            @RequestBody EncontroInput input,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return service.criar(input, auth).map(ResponseEntity::ok);
    }

    // ATUALIZAR ENCONTRO
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Object>> update(
            @PathVariable String id,
            @RequestBody EncontroInput input,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return service.atualizar(id, input, auth).map(ResponseEntity::ok);
    }

    // DELETAR ENCONTRO
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> delete(
            @PathVariable String id,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return service.deletar(id, auth).map(ResponseEntity::ok);
    }

    // MATCHING
    @PostMapping("/matching")
    public Mono<ResponseEntity<Object>> matching(
            @RequestBody MatchingInput input,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return service.matching(input, auth).map(ResponseEntity::ok);
    }

    // ATUALIZAR STATUS
    @PutMapping("/{id}/status")
    public Mono<ResponseEntity<Object>> status(
            @PathVariable String id,
            @RequestParam String status,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return service.atualizarStatus(id, status, auth).map(ResponseEntity::ok);
    }

    // LISTAR POR STATUS
    @GetMapping("/status/{status}")
    public Mono<ResponseEntity<Object>> porStatus(
            @PathVariable String status,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return service.buscarPorStatus(status, auth).map(ResponseEntity::ok);
    }

    // ADICIONAR PARTICIPANTE
    @PostMapping("/{id}/participantes/{usuarioId}")
    public Mono<ResponseEntity<Object>> addPart(
            @PathVariable String id,
            @PathVariable String usuarioId,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return service.adicionarParticipante(id, usuarioId, auth).map(ResponseEntity::ok);
    }

    // REMOVER PARTICIPANTE
    @DeleteMapping("/{id}/participantes/{usuarioId}")
    public Mono<ResponseEntity<Object>> remPart(
            @PathVariable String id,
            @PathVariable String usuarioId,
            @RequestHeader(value = "Authorization", required = false) String auth) {
        return service.removerParticipante(id, usuarioId, auth).map(ResponseEntity::ok);
    }
}
