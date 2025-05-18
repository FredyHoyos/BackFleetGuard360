CREATE TABLE driver(
    id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    FK_document_type BIGINT NOT NULL,
    CONSTRAINT fk_driver_document_type
        FOREIGN KEY (FK_document_type)
        REFERENCES document_type(id),
    document_number VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    phone_number VARCHAR(20),
    email VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    sex VARCHAR(10) NOT NULL,
    photo BYTEA,
    PRIMARY KEY(id)
);
CREATE SEQUENCE driver_seq START WITH 1 INCREMENT BY 50;