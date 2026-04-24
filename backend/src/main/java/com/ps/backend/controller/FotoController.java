package com.ps.backend.controller;

import com.ps.backend.model.Foto;
import com.ps.backend.service.FotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fotos")
public class FotoController {

    @Autowired
    private FotoService service;

    @GetMapping
    public ResponseEntity<List<Foto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Foto> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Foto> criar(@RequestBody @Valid Foto foto) {
        Foto salva = service.salvar(foto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Foto> atualizar(@PathVariable Long id, @RequestBody @Valid Foto foto) {
        return ResponseEntity.ok(service.atualizar(id, foto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

//adicionar @PreAuthorize("hasRole('ADMIN')") para proteger rotas de admin, e criar um controller separado para rotas de admin, ou seja, criar um AdminFotoController e colocar as rotas de admin lá, e deixar as rotas de listagem e busca no FotoController, para que os usuarios comuns possam acessar
