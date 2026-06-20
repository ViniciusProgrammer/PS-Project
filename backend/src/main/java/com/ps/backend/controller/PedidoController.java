package com.ps.backend.controller;

import com.ps.backend.dto.pedido.PedidoRequestDTO;
import com.ps.backend.dto.pedido.PedidoResponseDTO;
import com.ps.backend.model.Usuario;
import com.ps.backend.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criarPedido(
            @AuthenticationPrincipal Usuario usuario,
            @RequestBody PedidoRequestDTO dto) {
        PedidoResponseDTO pedido = pedidoService.criarPedido(usuario.getId(), dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos(
            @AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(pedidoService.listarPorUsuario(usuario.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPedido(
            @AuthenticationPrincipal Usuario usuario,
            @PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id, usuario.getId()));
    }
}
