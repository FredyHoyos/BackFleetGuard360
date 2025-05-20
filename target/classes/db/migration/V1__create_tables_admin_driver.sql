create table administrator (id bigint not null, name varchar(255), password varchar(255) not null, username varchar(255) not null, primary key (id));
create table document_type (id integer not null, name varchar(255), primary key (id));
create table driver (id bigint not null, address varchar(255) not null, birth_date timestamp(6) not null, document_number varchar(255) not null, email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, phone_number varchar(255) not null, photo bytea, rol varchar(255) not null, sex smallint check (sex between 0 and 2), username varchar(255) not null, fk_document_type integer not null, primary key (id));
create sequence administrator_seq start with 1 increment by 50;
create sequence driver_seq start with 1 increment by 50;
alter table if exists driver add constraint FKimq80ign0vkhnodrviwnbav5w foreign key (fk_document_type) references document_type;
