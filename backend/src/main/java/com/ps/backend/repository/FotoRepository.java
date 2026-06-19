package com.ps.backend.repository;

import com.ps.backend.model.Foto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FotoRepository extends JpaRepository<Foto, Long> {
    
    List<Foto> findByEventoId(Long eventoId);
    
}