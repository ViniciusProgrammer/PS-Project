package com.ps.backend.service;

import com.ps.backend.dto.carrinho.CarrinhoItemDTO;
import com.ps.backend.dto.carrinho.CarrinhoResponseDTO;
import com.ps.backend.exception.RecursoNaoEncontradoException;
import com.ps.backend.model.CarrinhoItem;
import com.ps.backend.model.Foto;
import com.ps.backend.model.Usuario;
import com.ps.backend.repository.CarrinhoItemRepository;
import com.ps.backend.repository.FotoRepository;
import com.ps.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarrinhoService {

    private final CarrinhoItemRepository carrinhoItemRepository;
    private final FotoRepository fotoRepository;
    private final UsuarioRepository usuarioRepository;

    public CarrinhoService(CarrinhoItemRepository carrinhoItemRepository,
                           FotoRepository fotoRepository,
                           UsuarioRepository usuarioRepository) {
        this.carrinhoItemRepository = carrinhoItemRepository;
        this.fotoRepository = fotoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public CarrinhoResponseDTO obterCarrinho(Long usuarioId) {
        List<CarrinhoItem> itens = carrinhoItemRepository.findByUsuarioId(usuarioId);

        List<CarrinhoItemDTO> itensDTO = itens.stream()
                .map(item -> new CarrinhoItemDTO(
                        item.getFoto().getId(),
                        item.getFoto().getNome(),
                        item.getFoto().getUrl(),
                        item.getFoto().getPreco()
                ))
                .toList();

        BigDecimal total = itensDTO.stream()
                .map(CarrinhoItemDTO::preco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CarrinhoResponseDTO(itensDTO, total);
    }

    @Transactional
    public CarrinhoResponseDTO adicionarItem(Long usuarioId, Long fotoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));

        Foto foto = fotoRepository.findById(fotoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Foto não encontrada"));

        boolean jaExiste = carrinhoItemRepository
                .findByUsuarioIdAndFotoId(usuarioId, fotoId)
                .isPresent();

        if (!jaExiste) {
            CarrinhoItem item = new CarrinhoItem();
            item.setUsuario(usuario);
            item.setFoto(foto);
            carrinhoItemRepository.save(item);
        }

        return obterCarrinho(usuarioId);
    }

    @Transactional
    public CarrinhoResponseDTO removerItem(Long usuarioId, Long fotoId) {
        carrinhoItemRepository.deleteByUsuarioIdAndFotoId(usuarioId, fotoId);
        return obterCarrinho(usuarioId);
    }

    @Transactional
    public void limparCarrinho(Long usuarioId) {
        carrinhoItemRepository.deleteByUsuarioId(usuarioId);
    }
}
