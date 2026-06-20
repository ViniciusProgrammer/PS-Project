package com.ps.backend.dto.carrinho;

import java.math.BigDecimal;

public record CarrinhoItemDTO(
        Long fotoId,
        String nomeFoto,
        String urlMarcaDagua,
        BigDecimal preco
) {}
