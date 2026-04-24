package com.ps.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.backend.dto.auth.LoginDTO;
import com.ps.backend.dto.auth.RegisterDTO;
import com.ps.backend.dto.auth.TokenDTO;
import com.ps.backend.model.Usuario;
import com.ps.backend.security.TokenService;
import com.ps.backend.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthService service;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO data) {

        var authToken = new UsernamePasswordAuthenticationToken(
            data.email(), data.senha()
        );

        var auth = authManager.authenticate(authToken);
        var usuario = (Usuario) auth.getPrincipal();

        var token = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO data) {
        service.registrar(data);
        return ResponseEntity.ok("Usuário criado");
    }
}
