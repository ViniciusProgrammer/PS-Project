package com.ps.backend.service;

import com.ps.backend.model.Evento;
import com.ps.backend.model.Foto;
import com.ps.backend.repository.EventoRepository;
import com.ps.backend.repository.FotoRepository;
import com.ps.backend.exception.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FotoService {

    @Autowired
    private FotoRepository repository;

    @Autowired
    private EventoRepository eventoRepository;

    public List<Foto> listar() {
        return repository.findAll();
    }

    public Foto buscarPorId(Long eventoId,Long fotoId) {

        Foto foto = repository.findById(fotoId)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Foto não encontrada"
                        )
                );

        if(!foto.getEvento().getId().equals(eventoId)){
            throw new RecursoNaoEncontradoException(
                    "Foto não pertence ao evento"
            );
        }

        return foto;
    }

    public Foto salvar(Foto foto, Long eventoId) {

        Evento evento = eventoRepository
                .findById(eventoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Evento não encontrado com ID: " + eventoId));

        foto.setEvento(evento);

        return repository.save(foto);
    }

    //Listar fotos por evento
    public List<Foto> listarPorEvento(Long eventoId){

        return repository.findByEventoId(eventoId);
    }

    public Foto atualizar(Long eventoId, Long fotoId, Foto foto) {
        Foto existente = buscarPorId(eventoId, fotoId);

        existente.setNome(foto.getNome());
        existente.setUrl(foto.getUrl());
        existente.setPreco(foto.getPreco());
        existente.setDescricao(foto.getDescricao());

        return repository.save(existente);
    }

    public void deletar(Long fotoId, Long eventoId) {
        Foto existente = buscarPorId(eventoId, fotoId);
        repository.delete(existente);
    }
}