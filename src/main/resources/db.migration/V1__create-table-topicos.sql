CREATE TABLE topicos (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         titulo VARCHAR(255) NOT NULL,
                         mensaje TEXT NOT NULL,
                         fecha_creacion datetime not null,
                         status BOOLEAN NOT NULL DEFAULT TRUE,
                         autor VARCHAR(255),
                         curso VARCHAR(255),
                         respuestas TEXT

    primary key (id)
);