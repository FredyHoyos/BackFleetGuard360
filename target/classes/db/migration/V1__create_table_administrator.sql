CREATE TABLE administrator (
    id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE administrator_seq START WITH 1 INCREMENT BY 50;