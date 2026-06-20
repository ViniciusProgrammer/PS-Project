package com.ps.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ps.backend.model.Pedido;

public interface PedidoRepository
        extends JpaRepository<Pedido, Long> {

    List<Pedido> findByUsuarioId(Long usuarioId);

}