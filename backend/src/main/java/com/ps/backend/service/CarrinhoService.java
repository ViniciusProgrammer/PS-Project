package com.ps.backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.backend.model.Carrinho;
import com.ps.backend.model.Foto;
import com.ps.backend.repository.CarrinhoRepository;
import com.ps.backend.repository.FotoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final FotoRepository fotoRepository;

    
    //Busca o carrinho pelo id do usuário.

    public Carrinho buscarPorUsuario(Long usuarioId) {
        return carrinhoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() ->
                        new RuntimeException("Carrinho não encontrado para o usuário."));
    }


    //Adiciona uma foto ao carrinho.

    public Carrinho adicionarFoto(Long usuarioId, Long fotoId) {

        Carrinho carrinho = buscarPorUsuario(usuarioId);

        Foto foto = fotoRepository.findById(fotoId)
                .orElseThrow(() ->
                        new RuntimeException("Foto não encontrada."));

        boolean existe = carrinho.getFotos()
                .stream()
                .anyMatch(f -> f.getId().equals(fotoId));

        if (!existe) {
            carrinho.getFotos().add(foto);
        }

        calcularValorFinal(carrinho);

        return carrinhoRepository.save(carrinho);
    }

    //Remove uma foto do carrinho.
    
    public Carrinho removerFoto(Long usuarioId, Long fotoId) {

        Carrinho carrinho = buscarPorUsuario(usuarioId);

        carrinho.getFotos()
                .removeIf(f -> f.getId().equals(fotoId));

        calcularValorFinal(carrinho);

        return carrinhoRepository.save(carrinho);
    }

    //Limpa completamente o carrinho.

    public Carrinho limparCarrinho(Long usuarioId) {

        Carrinho carrinho = buscarPorUsuario(usuarioId);

        carrinho.getFotos().clear();
        carrinho.setValorFinal(0.0);
        carrinho.setDesconto(0.0);

        return carrinhoRepository.save(carrinho);
    }

    //Aplica desconto ao carrinho.
    
    public Carrinho aplicarDesconto(Long usuarioId, Double desconto) {

        Carrinho carrinho = buscarPorUsuario(usuarioId);

        carrinho.setDesconto(desconto);

        calcularValorFinal(carrinho);

        return carrinhoRepository.save(carrinho);
    }

    // Busca o carrinho pelo ID.

    public Carrinho buscarPorId(Long carrinhoId) {

        return carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() ->
                        new RuntimeException("Carrinho não encontrado."));
    }

    // Salva alterações no carrinho.

    public Carrinho salvar(Carrinho carrinho) {

        calcularValorFinal(carrinho);

        return carrinhoRepository.save(carrinho);
    }

    //Calcula o valor final considerando desconto.

    private void calcularValorFinal(Carrinho carrinho) {

        double subtotal = carrinho.getFotos()
                .stream()
                .mapToDouble(Foto::getPreco)
                .sum();

        int quantidadeFotos = carrinho.getFotos().size();

        double percentualDesconto = 0.0;

        if (quantidadeFotos >= 3 && quantidadeFotos <= 5) {
            percentualDesconto = 0.05;
        } else if (quantidadeFotos >= 6) {
            percentualDesconto = 0.10;
        }

        double valorDesconto = subtotal * percentualDesconto;
        double valorFinal = subtotal - valorDesconto;

        carrinho.setDesconto(valorDesconto);
        carrinho.setValorFinal(valorFinal);
    }
}