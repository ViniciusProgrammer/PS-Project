package com.ps.backend.repository;
import com.ps.backend.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

}
