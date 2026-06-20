package com.ps.backend.dto.pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.ps.backend.model.StatusPedido;

public record PedidoResponseDTO(
        Long id,
        BigDecimal total,
        StatusPedido status,
        LocalDateTime criadoEm,
        List<ItemPedidoResponseDTO> itens
) {
}