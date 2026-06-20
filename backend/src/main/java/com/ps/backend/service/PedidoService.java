package com.ps.backend.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.backend.dto.pedido.ItemPedidoResponseDTO;
import com.ps.backend.dto.pedido.PedidoRequestDTO;
import com.ps.backend.dto.pedido.PedidoResponseDTO;
import com.ps.backend.model.Foto;
import com.ps.backend.model.ItemPedido;
import com.ps.backend.model.Pedido;
import com.ps.backend.model.Usuario;
import com.ps.backend.repository.FotoRepository;
import com.ps.backend.repository.PedidoRepository;
import com.ps.backend.repository.UsuarioRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final FotoRepository fotoRepository;

    public PedidoService(
            PedidoRepository pedidoRepository,
            UsuarioRepository usuarioRepository,
            FotoRepository fotoRepository) {

        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.fotoRepository = fotoRepository;
    }

    @Transactional
    public PedidoResponseDTO criarPedido(
            Long usuarioId,
            PedidoRequestDTO dto) {

        Usuario usuario = usuarioRepository
                .findById(usuarioId)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        if (dto.fotosIds() == null || dto.fotosIds().isEmpty()) {
            throw new RuntimeException(
                    "O pedido deve possuir ao menos uma foto");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);

        List<ItemPedido> itens = new ArrayList<>();
        BigDecimal subtotal = BigDecimal.ZERO;

        for (Long fotoId : dto.fotosIds()) {

            Foto foto = fotoRepository
                    .findById(fotoId)
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Foto não encontrada: " + fotoId));

            ItemPedido item = new ItemPedido();

            item.setPedido(pedido);
            item.setFoto(foto);
            item.setPrecoUnitario(foto.getPreco());

            itens.add(item);

            subtotal = subtotal.add(foto.getPreco());
        }

        BigDecimal desconto =
                calcularDesconto(subtotal, itens.size());

        BigDecimal total = subtotal.subtract(desconto);

        pedido.setItens(itens);
        pedido.setTotal(total);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return converterParaDTO(pedidoSalvo);
    }

    private PedidoResponseDTO converterParaDTO(
            Pedido pedido) {

        List<ItemPedidoResponseDTO> itens =
                pedido.getItens()
                        .stream()
                        .map(item -> new ItemPedidoResponseDTO(
                                item.getFoto().getId(),
                                item.getFoto().getNome(),
                                item.getPrecoUnitario()
                        ))
                        .toList();

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getTotal(),
                pedido.getStatus(),
                pedido.getCriadoEm(),
                itens
        );
    }

    private BigDecimal calcularDesconto(
            BigDecimal subtotal,
            int quantidadeFotos) {

        if (quantidadeFotos >= 6) {
            return subtotal
                    .multiply(new BigDecimal("0.10"))
                    .setScale(2, RoundingMode.HALF_UP);
        }

        if (quantidadeFotos >= 3) {
            return subtotal
                    .multiply(new BigDecimal("0.05"))
                    .setScale(2, RoundingMode.HALF_UP);
        }

        return BigDecimal.ZERO;
    }
}