package com.ps.backend.service;

import com.ps.backend.dto.carrinho.CarrinhoResponseDTO;
import com.ps.backend.exception.RecursoNaoEncontradoException;
import com.ps.backend.model.CarrinhoItem;
import com.ps.backend.model.Foto;
import com.ps.backend.model.Usuario;
import com.ps.backend.repository.CarrinhoItemRepository;
import com.ps.backend.repository.FotoRepository;
import com.ps.backend.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarrinhoServiceTest {

    @Mock
    private CarrinhoItemRepository carrinhoItemRepository;

    @Mock
    private FotoRepository fotoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CarrinhoService carrinhoService;

    @Test
    void deveRetornarCarrinhoVazioParaUsuarioSemItens() {
        when(carrinhoItemRepository.findByUsuarioId(1L)).thenReturn(List.of());

        CarrinhoResponseDTO resposta = carrinhoService.obterCarrinho(1L);

        assertTrue(resposta.itens().isEmpty());
        assertEquals(BigDecimal.ZERO, resposta.total());
    }

    @Test
    void deveAdicionarItemAoCarrinho() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Foto foto = new Foto();
        foto.setId(10L);
        foto.setNome("Foto 1");
        foto.setUrl("http://exemplo.com/foto1.jpg");
        foto.setPreco(new BigDecimal("12.99"));

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(fotoRepository.findById(10L)).thenReturn(Optional.of(foto));
        when(carrinhoItemRepository.findByUsuarioIdAndFotoId(1L, 10L)).thenReturn(Optional.empty());

        CarrinhoItem itemSalvo = new CarrinhoItem();
        itemSalvo.setUsuario(usuario);
        itemSalvo.setFoto(foto);

        when(carrinhoItemRepository.save(any(CarrinhoItem.class))).thenReturn(itemSalvo);
        when(carrinhoItemRepository.findByUsuarioId(1L)).thenReturn(List.of(itemSalvo));

        CarrinhoResponseDTO resposta = carrinhoService.adicionarItem(1L, 10L);

        assertEquals(1, resposta.itens().size());
        assertEquals(new BigDecimal("12.99"), resposta.total());
        verify(carrinhoItemRepository, times(1)).save(any(CarrinhoItem.class));
    }

    @Test
    void naoDeveDuplicarItemJaExistenteNoCarrinho() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Foto foto = new Foto();
        foto.setId(10L);
        foto.setNome("Foto 1");
        foto.setUrl("http://exemplo.com/foto1.jpg");
        foto.setPreco(new BigDecimal("12.99"));

        CarrinhoItem itemExistente = new CarrinhoItem();
        itemExistente.setUsuario(usuario);
        itemExistente.setFoto(foto);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(fotoRepository.findById(10L)).thenReturn(Optional.of(foto));
        when(carrinhoItemRepository.findByUsuarioIdAndFotoId(1L, 10L))
                .thenReturn(Optional.of(itemExistente));
        when(carrinhoItemRepository.findByUsuarioId(1L)).thenReturn(List.of(itemExistente));

        CarrinhoResponseDTO resposta = carrinhoService.adicionarItem(1L, 10L);

        assertEquals(1, resposta.itens().size());
        verify(carrinhoItemRepository, never()).save(any(CarrinhoItem.class));
    }

    @Test
    void deveLancarExcecaoQuandoFotoNaoEncontrada() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(fotoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RecursoNaoEncontradoException.class,
                () -> carrinhoService.adicionarItem(1L, 99L));
    }

    @Test
    void deveRemoverItemDoCarrinho() {
        when(carrinhoItemRepository.findByUsuarioId(1L)).thenReturn(List.of());

        CarrinhoResponseDTO resposta = carrinhoService.removerItem(1L, 10L);

        verify(carrinhoItemRepository, times(1)).deleteByUsuarioIdAndFotoId(1L, 10L);
        assertTrue(resposta.itens().isEmpty());
    }
}
