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

import com.falae.bff.dto.FeedbackDtos.FeedbackInput;
import com.falae.bff.service.FeedbacksService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bff/feedbacks")
public class FeedbacksBffController {

    @Autowired
    private FeedbacksService service;

    @GetMapping
    public Mono<ResponseEntity<Object>> list(@RequestHeader(value="Authorization", required=false) String auth) {
        return service.listar(auth).map(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Object>> get(@PathVariable String id,
            @RequestHeader(value="Authorization", required=false) String auth) {
        return service.obter(id, auth).map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<Object>> create(@RequestBody FeedbackInput input,
            @RequestHeader(value="Authorization", required=false) String auth) {
        return service.criar(input, auth).map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Object>> update(@PathVariable String id, @RequestBody FeedbackInput input,
            @RequestHeader(value="Authorization", required=false) String auth) {
        return service.atualizar(id, input, auth).map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> delete(@PathVariable String id,
            @RequestHeader(value="Authorization", required=false) String auth) {
        return service.deletar(id, auth).map(ResponseEntity::ok);
    }

    @GetMapping("/encontro/{encontroId}")
    public Mono<ResponseEntity<Object>> porEncontro(@PathVariable String encontroId,
            @RequestHeader(value="Authorization", required=false) String auth) {
        return service.porEncontro(encontroId, auth).map(ResponseEntity::ok);
    }

    @GetMapping("/usuario/{usuarioId}")
    public Mono<ResponseEntity<Object>> porUsuario(@PathVariable String usuarioId,
            @RequestHeader(value="Authorization", required=false) String auth) {
        return service.porUsuario(usuarioId, auth).map(ResponseEntity::ok);
    }

    @GetMapping("/verificar/{encontroId}/{usuarioId}")
    public Mono<ResponseEntity<Object>> verificar(
            @PathVariable String encontroId,
            @PathVariable String usuarioId,
            @RequestHeader(value="Authorization", required=false) String auth) {
        return service.verificar(encontroId, usuarioId, auth).map(ResponseEntity::ok);
    }
}
