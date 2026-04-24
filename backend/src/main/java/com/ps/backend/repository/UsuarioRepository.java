package com.ps.backend.repository;
import com.ps.backend.model.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    
    Optional<Usuario> findByEmail(String email);

}
