package com.ps.backend.controller;

import com.ps.backend.model.Foto;
import com.ps.backend.service.FotoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fotos")
public class FotoController {

    private final FotoService service;

    public FotoController(FotoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Foto> listar() {
        return service.listarFotos();
    }
}