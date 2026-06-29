package com.ps.backend.controller;

import com.ps.backend.dto.pedido.PedidoRequestDTO;
import com.ps.backend.dto.pedido.PedidoResponseDTO;
import com.ps.backend.model.Usuario;
import com.ps.backend.service.CarrinhoService;
import com.ps.backend.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final CarrinhoService carrinhoService;

    public PedidoController(PedidoService pedidoService, CarrinhoService carrinhoService) {
        this.pedidoService = pedidoService;
        this.carrinhoService = carrinhoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criarPedido(
            @AuthenticationPrincipal Usuario usuario,
            @RequestBody PedidoRequestDTO dto) {
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        PedidoResponseDTO pedido = pedidoService.criarPedido(usuario.getId(), dto);
        carrinhoService.limparCarrinho(usuario.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos(
            @AuthenticationPrincipal Usuario usuario) {
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(pedidoService.listarPorUsuario(usuario.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPedido(
            @AuthenticationPrincipal Usuario usuario,
            @PathVariable Long id) {
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(pedidoService.buscarPorId(id, usuario.getId()));
    }
}
