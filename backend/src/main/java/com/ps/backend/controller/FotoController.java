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
@RequestMapping("/eventos/{eventoId}/fotos")
public class FotoController {

    @Autowired
    private FotoService service;

    @GetMapping
    public ResponseEntity<List<Foto>> listar(@PathVariable Long eventoId) {

        return ResponseEntity.ok(
            service.listarPorEvento(eventoId)
        );
    }

    @GetMapping("/{fotoId}")
    public ResponseEntity<Foto> buscar( @PathVariable Long eventoId, @PathVariable Long fotoId) {

        return ResponseEntity.ok(
            service.buscarPorId(fotoId)
        );
    }

    @PostMapping
    public ResponseEntity<Foto> criar(@PathVariable Long eventoId, @RequestBody @Valid Foto foto) {

        Foto salva = service.salvar(foto, eventoId);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(salva);
    }

    @PutMapping("/{fotoId}")
    public ResponseEntity<Foto> atualizar(
        @PathVariable Long eventoId, @PathVariable Long fotoId, @RequestBody @Valid Foto foto) {

        return ResponseEntity.ok(
            service.atualizar(eventoId, fotoId, foto)
        );
    }

    @DeleteMapping("/{fotoId}")
    public ResponseEntity<Void> deletar(@PathVariable Long eventoId, @PathVariable Long fotoId) {

        service.deletar(eventoId, fotoId);

        return ResponseEntity.noContent().build();
    }
}