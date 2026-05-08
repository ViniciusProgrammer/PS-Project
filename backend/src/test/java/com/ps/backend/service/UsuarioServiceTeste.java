package com.ps.backend.service;

import com.ps.backend.model.Usuario;
import com.ps.backend.repository.UsuarioRepository;
import com.ps.backend.service.UsuarioService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;
    
    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UsuarioService service;

    @Test
    void deveSalvarUsuario() {

    // Falha para TDD
        /* 
        Usuario usuario = new Usuario();
        usuario.setNome("João");
        usuario.setEmail("joao@email.com");
        usuario.setSenha("123");

        when(repository.save(any(Usuario.class))).thenThrow(new RuntimeException("Erro proposital"));

        Usuario resultado = service.salvar(usuario);

        assertEquals("Maria", resultado.getNome()); //
        */

        //Teste certo 
    
        Usuario usuario = new Usuario();
        usuario.setNome("João");
        usuario.setEmail("joao@email.com");
        usuario.setSenha("123");

        when(encoder.encode("123")).thenReturn("senha-criptografada");

        Usuario usuarioSalvo = new Usuario();
        usuarioSalvo.setId(1L);
        usuarioSalvo.setNome("João");
        usuarioSalvo.setEmail("joao@email.com");
        usuarioSalvo.setSenha("senha-criptografada");

        when(repository.save(any(Usuario.class))).thenReturn(usuarioSalvo);

        Usuario resultado = service.salvar(usuario);

        assertEquals(1L, resultado.getId());
        assertEquals("João", resultado.getNome());
        assertEquals("senha-criptografada", resultado.getSenha());

        verify(encoder, times(1)).encode("123");
        verify(repository, times(1)).save(any(Usuario.class));

    }
}