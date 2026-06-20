package com.ps.backend.service;

import com.ps.backend.dto.pedido.PedidoRequestDTO;
import com.ps.backend.dto.pedido.PedidoResponseDTO;
import com.ps.backend.exception.RecursoNaoEncontradoException;
import com.ps.backend.model.*;
import com.ps.backend.repository.FotoRepository;
import com.ps.backend.repository.PedidoRepository;
import com.ps.backend.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private FotoRepository fotoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    void deveCriarPedidoComUmaFoto() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Foto foto = new Foto();
        foto.setId(10L);
        foto.setNome("Foto Corrida");
        foto.setPreco(new BigDecimal("15.00"));

        Pedido pedidoSalvo = new Pedido();
        pedidoSalvo.setId(100L);
        pedidoSalvo.setUsuario(usuario);
        pedidoSalvo.setTotal(new BigDecimal("15.00"));
        pedidoSalvo.setStatus(StatusPedido.PAGO);
        pedidoSalvo.setCriadoEm(LocalDateTime.now());

        ItemPedido item = new ItemPedido();
        item.setFoto(foto);
        item.setPrecoUnitario(foto.getPreco());
        item.setPedido(pedidoSalvo);
        pedidoSalvo.setItens(List.of(item));

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(fotoRepository.findById(10L)).thenReturn(Optional.of(foto));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoSalvo);

        PedidoRequestDTO dto = new PedidoRequestDTO(List.of(10L));
        PedidoResponseDTO resposta = pedidoService.criarPedido(1L, dto);

        assertNotNull(resposta);
        assertEquals(new BigDecimal("15.00"), resposta.total());
        assertEquals(StatusPedido.PAGO, resposta.status());
        assertEquals(1, resposta.itens().size());
    }

    @Test
    void deveAplicarDescontoDe5PorCentoParaTresFotos() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        List<Long> fotosIds = new ArrayList<>();
        List<Foto> fotos = new ArrayList<>();

        for (long i = 1; i <= 3; i++) {
            Foto foto = new Foto();
            foto.setId(i);
            foto.setNome("Foto " + i);
            foto.setPreco(new BigDecimal("10.00"));
            fotos.add(foto);
            fotosIds.add(i);
            when(fotoRepository.findById(i)).thenReturn(Optional.of(foto));
        }

        Pedido pedidoSalvo = new Pedido();
        pedidoSalvo.setId(200L);
        pedidoSalvo.setUsuario(usuario);
        pedidoSalvo.setTotal(new BigDecimal("28.50")); // 30 - 5% = 28.50
        pedidoSalvo.setStatus(StatusPedido.PAGO);
        pedidoSalvo.setCriadoEm(LocalDateTime.now());

        List<ItemPedido> itens = fotos.stream().map(f -> {
            ItemPedido item = new ItemPedido();
            item.setFoto(f);
            item.setPrecoUnitario(f.getPreco());
            item.setPedido(pedidoSalvo);
            return item;
        }).toList();
        pedidoSalvo.setItens(itens);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoSalvo);

        PedidoRequestDTO dto = new PedidoRequestDTO(fotosIds);
        PedidoResponseDTO resposta = pedidoService.criarPedido(1L, dto);

        assertEquals(new BigDecimal("28.50"), resposta.total());
    }

    @Test
    void deveLancarExcecaoQuandoListaDeFotosVazia() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        PedidoRequestDTO dto = new PedidoRequestDTO(List.of());

        assertThrows(IllegalArgumentException.class,
                () -> pedidoService.criarPedido(1L, dto));
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        PedidoRequestDTO dto = new PedidoRequestDTO(List.of(1L));

        assertThrows(RecursoNaoEncontradoException.class,
                () -> pedidoService.criarPedido(99L, dto));
    }
}
