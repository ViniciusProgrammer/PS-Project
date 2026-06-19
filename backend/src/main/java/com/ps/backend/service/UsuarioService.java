package com.ps.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ps.backend.dto.auth.RegisterDTO;
import com.ps.backend.dto.auth.UpdateUserDTO;
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


        //se email já existir, lançar exceção
        if (repository.findByEmail(data.email()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
            
        } else {

            Usuario usuario = new Usuario();
            usuario.setNome(data.nome());
            usuario.setEmail(data.email());
            usuario.setSenha(senhaCriptografada);

            salvar(usuario);
        }

        return;
    }

    public Usuario updateUsuario(Long id, UpdateUserDTO data) {
        
        Usuario usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("Problemas para encontrar o usuário!"));

        usuario.setNome(data.nome());
        usuario.setEmail(data.email());

        //TODO: cadastrar evento que dispara confirmação do novo email atualizado!
        
        return repository.save(usuario);
    }

    public Usuario salvar(Usuario usuario) {

        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    
    }


}