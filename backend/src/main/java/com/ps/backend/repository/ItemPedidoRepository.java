package com.ps.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ps.backend.model.ItemPedido;

public interface ItemPedidoRepository
        extends JpaRepository<ItemPedido, Long> {

}