CREATE TABLE itens_pedido (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    pedido_id       BIGINT NOT NULL,
    foto_id         BIGINT NOT NULL,
    preco_unitario  DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    FOREIGN KEY (foto_id) REFERENCES fotos(id)
);