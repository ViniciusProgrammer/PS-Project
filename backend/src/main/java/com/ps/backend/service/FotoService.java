package com.ps.backend.service;

import com.ps.backend.model.Foto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FotoService {

    public List<Foto> listarFotos() {
        return List.of(
                new Foto(1L, "Corrida 1", "Evento A", "url1"),
                new Foto(2L, "Corrida 2", "Evento B", "url2")
        );
    }
}
