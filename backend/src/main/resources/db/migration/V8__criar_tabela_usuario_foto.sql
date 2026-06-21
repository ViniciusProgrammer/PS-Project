CREATE TABLE usuario_foto (
    usuario_id BIGINT NOT NULL,
    foto_id BIGINT NOT NULL,

    PRIMARY KEY (usuario_id, foto_id),

    CONSTRAINT fk_usuario_foto_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuario(id),

    CONSTRAINT fk_usuario_foto_foto
        FOREIGN KEY (foto_id)
        REFERENCES foto(id)
);