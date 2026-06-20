package com.ps.backend.repository;

import com.ps.backend.model.CarrinhoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarrinhoItemRepository extends JpaRepository<CarrinhoItem, Long> {

    List<CarrinhoItem> findByUsuarioId(Long usuarioId);

    Optional<CarrinhoItem> findByUsuarioIdAndFotoId(Long usuarioId, Long fotoId);

    void deleteByUsuarioIdAndFotoId(Long usuarioId, Long fotoId);

    void deleteByUsuarioId(Long usuarioId);
}
