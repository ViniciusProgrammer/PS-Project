package com.ps.backend.service;

import com.ps.backend.dto.auth.RegisterDTO;
import com.ps.backend.dto.auth.UpdateUserDTO;
import com.ps.backend.exception.UsuarioJaExisteException;
import com.ps.backend.model.Role;
import com.ps.backend.model.Usuario;
import com.ps.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public Usuario cadastrar(RegisterDTO data) {
        if (repository.findByEmail(data.email()).isPresent()) {
            throw new UsuarioJaExisteException();
        }

        Usuario usuario = new Usuario();
        usuario.setNome(data.nome());
        usuario.setEmail(data.email());
        usuario.setSenha(encoder.encode(data.senha()));
        usuario.setRole(data.role() != null ? data.role() : Role.USER);

        return repository.save(usuario);
    }

    public Usuario updateUsuario(Long id, UpdateUserDTO data) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(data.nome());
        usuario.setEmail(data.email());

        return repository.save(usuario);
    }

    public Usuario salvar(Usuario usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }
}
