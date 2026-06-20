package com.ps.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ps.backend.dto.pedido.PedidoRequestDTO;
import com.ps.backend.dto.pedido.PedidoResponseDTO;
import com.ps.backend.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criarPedido(
            @RequestParam Long usuarioId,
            @RequestBody PedidoRequestDTO dto) {

        PedidoResponseDTO pedido =
                pedidoService.criarPedido(usuarioId, dto);

        return ResponseEntity.ok(pedido);
    }
}