package com.ps.backend.controller;

import com.ps.backend.dto.carrinho.CarrinhoResponseDTO;
import com.ps.backend.model.Usuario;
import com.ps.backend.service.CarrinhoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {
    private final CarrinhoService carrinhoService;
    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping
    public ResponseEntity<CarrinhoResponseDTO> obterCarrinho(
            @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(carrinhoService.obterCarrinho(usuario.getId()));
    }

    @PostMapping("/{fotoId}")
    public ResponseEntity<CarrinhoResponseDTO> adicionarItem(
            @AuthenticationPrincipal Usuario usuario,
            @PathVariable Long fotoId) {
        return ResponseEntity.ok(carrinhoService.adicionarItem(usuario.getId(), fotoId));
    }

    @DeleteMapping("/{fotoId}")
    public ResponseEntity<CarrinhoResponseDTO> removerItem(
            @AuthenticationPrincipal Usuario usuario,
            @PathVariable Long fotoId) {
        return ResponseEntity.ok(carrinhoService.removerItem(usuario.getId(), fotoId));
    }
}
