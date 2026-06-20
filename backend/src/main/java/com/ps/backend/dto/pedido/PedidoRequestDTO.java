package com.ps.backend.dto.pedido;

import java.util.List;

public record PedidoRequestDTO(
        List<Long> fotosIds
) {
}