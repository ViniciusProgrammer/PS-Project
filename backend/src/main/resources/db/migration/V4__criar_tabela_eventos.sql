CREATE TABLE eventos (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo       VARCHAR(200) NOT NULL,
    descricao    TEXT,
    data_evento  DATETIME NOT NULL,
    local        VARCHAR(200),
    cidade       VARCHAR(100),
    pais         VARCHAR(80) DEFAULT 'Brasil',
    categoria_id BIGINT,
    fotografo    VARCHAR(100),
    criado_em    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);