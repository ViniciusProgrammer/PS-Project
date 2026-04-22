CREATE TABLE foto
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome      VARCHAR(255) NOT NULL,
    url       VARCHAR(255) NOT NULL,
    preco DOUBLE NOT NULL,
    descricao VARCHAR(255)
);