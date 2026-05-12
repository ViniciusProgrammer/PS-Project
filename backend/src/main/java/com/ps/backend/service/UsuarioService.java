package com.ps.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ps.backend.dto.auth.RegisterDTO;
import com.ps.backend.model.Usuario;
import com.ps.backend.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public void cadastrar(RegisterDTO data) {
        //criptografou a senha
        String senhaCriptografada = encoder.encode(data.senha());

        Usuario usuario = new Usuario();
        usuario.setNome(data.nome());
        usuario.setEmail(data.email());
        usuario.setSenha(senhaCriptografada);

        salvar(usuario);
    }

    public Usuario salvar(Usuario usuario) {

        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    
    }


}