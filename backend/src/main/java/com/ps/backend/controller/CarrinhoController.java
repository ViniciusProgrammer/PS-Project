package com.ps.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.ps.backend.model.Carrinho;
import com.ps.backend.model.Usuario;
import com.ps.backend.service.CarrinhoService;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    private final CarrinhoService service;

    public CarrinhoController(CarrinhoService service) {
        this.service = service;
    }

    @GetMapping("/me")
    public ResponseEntity<Carrinho> meuCarrinho(Authentication authentication) {

        Usuario usuario = (Usuario) authentication.getPrincipal();

        Carrinho carrinho = service.buscarCarrinhoDoUsuario(usuario.getId());
        //Corrigir aqui
        
        return ResponseEntity.ok(carrinho);
    }

    @PostMapping("/fotos/{fotoId}")
    public ResponseEntity<Void> adicionarFoto(
            @PathVariable Long fotoId,
            Authentication authentication) {

        Usuario usuario = (Usuario) authentication.getPrincipal();

        service.adicionarFoto(usuario.getId(), fotoId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/fotos/{fotoId}")
    public ResponseEntity<Void> removerFoto(
            @PathVariable Long fotoId,
            Authentication authentication) {

        Usuario usuario = (Usuario) authentication.getPrincipal();

        service.removerFoto(usuario.getId(), fotoId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/checkout")
    public ResponseEntity<Void> finalizarCompra(
            Authentication authentication) {

        Usuario usuario = (Usuario) authentication.getPrincipal();

        service.finalizarCompra(usuario.getId());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/limpar")
    public ResponseEntity<Void> limparCarrinho(
            Authentication authentication) {

        Usuario usuario = (Usuario) authentication.getPrincipal();

        service.limparCarrinho(usuario.getId());

        return ResponseEntity.ok().build();
    }
}