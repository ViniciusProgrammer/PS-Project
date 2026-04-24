package com.ps.backend.service;

import com.ps.backend.model.Foto;
import com.ps.backend.repository.FotoRepository;
import com.ps.backend.exception.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FotoService {

    @Autowired
    private FotoRepository repository;

    public List<Foto> listar() {
        return repository.findAll();
    }

    public Foto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Foto não encontrada com ID: " + id));
    }

    public Foto salvar(Foto foto) {
        return repository.save(foto);
    }

    public Foto atualizar(Long id, Foto foto) {
        Foto existente = buscarPorId(id);

        existente.setNome(foto.getNome());
        existente.setUrl(foto.getUrl());
        existente.setPreco(foto.getPreco());
        existente.setDescricao(foto.getDescricao());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        Foto existente = buscarPorId(id);
        repository.delete(existente);
    }
}