CREATE TABLE usuarios(

    id       BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    usuario  VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(300) NOT NULL ,


    primary key (id)
);