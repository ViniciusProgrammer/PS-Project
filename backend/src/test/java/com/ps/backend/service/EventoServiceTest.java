package com.ps.backend.service;

import com.ps.backend.exception.RecursoNaoEncontradoException;
import com.ps.backend.model.Categoria;
import com.ps.backend.model.Evento;
import com.ps.backend.repository.EventoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventoServiceTest {

    @Mock
    private EventoRepository eventoRepository;

    @InjectMocks
    private EventoService eventoService;

    @Test
    void deveListarTodosOsEventosSemFiltro() {
        Evento e1 = criarEvento(1L, "Corrida MTB Recife", null);
        Evento e2 = criarEvento(2L, "Crossfit Games SP", null);

        when(eventoRepository.findAll()).thenReturn(List.of(e1, e2));

        List<Evento> resultado = eventoService.listar(null, null);

        assertEquals(2, resultado.size());
    }

    @Test
    void deveFiltrarEventosPorBusca() {
        Evento e1 = criarEvento(1L, "Corrida MTB Recife", null);
        Evento e2 = criarEvento(2L, "Crossfit Games SP", null);

        when(eventoRepository.findAll()).thenReturn(List.of(e1, e2));

        List<Evento> resultado = eventoService.listar(null, "MTB");

        assertEquals(1, resultado.size());
        assertEquals("Corrida MTB Recife", resultado.get(0).getTitulo());
    }

    @Test
    void deveFiltrarEventosPorCategoria() {
        Categoria cat1 = new Categoria();
        cat1.setId(1L);
        cat1.setNome("Ciclismo");

        Categoria cat2 = new Categoria();
        cat2.setId(2L);
        cat2.setNome("Crossfit");

        Evento e1 = criarEvento(1L, "Corrida MTB Recife", cat1);
        Evento e2 = criarEvento(2L, "Crossfit Games SP", cat2);

        when(eventoRepository.findAll()).thenReturn(List.of(e1, e2));

        List<Evento> resultado = eventoService.listar(1L, null);

        assertEquals(1, resultado.size());
        assertEquals("Corrida MTB Recife", resultado.get(0).getTitulo());
    }

    @Test
    void deveBuscarEventoPorId() {
        Evento evento = criarEvento(1L, "Corrida MTB Recife", null);

        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));

        Evento resultado = eventoService.buscarPorId(1L);

        assertEquals("Corrida MTB Recife", resultado.getTitulo());
    }

    @Test
    void deveLancarExcecaoQuandoEventoNaoEncontrado() {
        when(eventoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RecursoNaoEncontradoException.class,
                () -> eventoService.buscarPorId(99L));
    }

    private Evento criarEvento(Long id, String titulo, Categoria categoria) {
        Evento evento = new Evento();
        evento.setId(id);
        evento.setTitulo(titulo);
        evento.setDataEvento(LocalDateTime.now());
        evento.setCategoria(categoria);
        return evento;
    }
}
