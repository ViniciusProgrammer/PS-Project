package com.ps.backend.dto.pedido;

import java.math.BigDecimal;

public record ItemPedidoResponseDTO(
        Long fotoId,
        String nomeFoto,
        BigDecimal precoUnitario
) {
}