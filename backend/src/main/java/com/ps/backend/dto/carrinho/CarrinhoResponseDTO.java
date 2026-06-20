package com.ps.backend.dto.carrinho;

import java.math.BigDecimal;
import java.util.List;

public record CarrinhoResponseDTO(
        List<CarrinhoItemDTO> itens,
        BigDecimal total
) {}
