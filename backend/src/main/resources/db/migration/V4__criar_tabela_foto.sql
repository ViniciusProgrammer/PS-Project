CREATE TABLE foto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    descricao VARCHAR(255),
    evento_id BIGINT,
    CONSTRAINT fk_foto_evento
        FOREIGN KEY (evento_id)
        REFERENCES eventos(id)
);