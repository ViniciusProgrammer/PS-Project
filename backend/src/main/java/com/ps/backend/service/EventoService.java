package com.ps.backend.service;

import com.ps.backend.exception.RecursoNaoEncontradoException;
import com.ps.backend.model.Evento;
import com.ps.backend.repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoService {
    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> listar(Long categoriaId, String busca) {
        List<Evento> eventos = eventoRepository.findAll();

        if (categoriaId != null) {
            eventos = eventos.stream()
                    .filter(e -> e.getCategoria() != null
                            && e.getCategoria().getId().equals(categoriaId))
                    .collect(Collectors.toList());
        }

        if (busca != null && !busca.isBlank()) {
            String termoBusca = busca.toLowerCase();
            eventos = eventos.stream()
                    .filter(e -> e.getTitulo().toLowerCase().contains(termoBusca)
                            || (e.getDescricao() != null
                            && e.getDescricao().toLowerCase().contains(termoBusca))
                            || (e.getCidade() != null
                            && e.getCidade().toLowerCase().contains(termoBusca)))
                    .collect(Collectors.toList());
        }

        return eventos;
    }

    public Evento buscarPorId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Evento não encontrado com ID: " + id));
    }

    public Evento salvar(Evento evento) {
        return eventoRepository.save(evento);
    }

    public Evento atualizar(Long id, Evento dados) {
        Evento existente = buscarPorId(id);
        existente.setTitulo(dados.getTitulo());
        existente.setDescricao(dados.getDescricao());
        existente.setDataEvento(dados.getDataEvento());
        existente.setLocal(dados.getLocal());
        existente.setCidade(dados.getCidade());
        existente.setPais(dados.getPais());
        existente.setCategoria(dados.getCategoria());
        existente.setFotografo(dados.getFotografo());

        return eventoRepository.save(existente);
    }

    public void deletar(Long id) {
        Evento existente = buscarPorId(id);
        eventoRepository.delete(existente);
    }
}
