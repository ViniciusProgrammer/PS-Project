package com.ps.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ps.backend.dto.auth.RegisterDTO;
import com.ps.backend.exception.UsuarioJaExisteException;
import com.ps.backend.model.Role;
import com.ps.backend.model.Usuario;
import com.ps.backend.repository.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    PasswordEncoder pwdEncoder;

    public Usuario registrar(RegisterDTO data) {
        if (repository.findByEmail(data.email()).isPresent()) {
            throw new UsuarioJaExisteException();
        }

        Usuario usuario = new Usuario();
        usuario.setNome(data.nome());
        usuario.setEmail(data.email());
        usuario.setSenha(pwdEncoder.encode(data.senha()));
        usuario.setRole(data.role() != null ? data.role() : Role.USER);

        return repository.save(usuario);
    }
}