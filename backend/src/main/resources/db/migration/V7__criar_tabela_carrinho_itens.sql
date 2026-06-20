CREATE TABLE carrinho_itens (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id     BIGINT NOT NULL,
    foto_id        BIGINT NOT NULL,
    adicionado_em  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uq_usuario_foto (usuario_id, foto_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (foto_id) REFERENCES foto(id)
);