package com.ps.backend.repository;

//Importando Class Evento e JpaRepository
import com.ps.backend.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {

}
