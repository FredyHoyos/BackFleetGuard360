create table assigment (id bigint not null, is_shift_completed boolean, fk_driver bigint not null, fk_driver_2 bigint not null, fk_shift bigint not null, primary key (id));
create table shift (id bigint not null, date date not null, end_time time(6) not null, route varchar(255) not null, start_time time(6) not null, worked_hours_in_shift integer not null, primary key (id));
create sequence assigment_seq start with 1 increment by 50;
create sequence shift_seq start with 1 increment by 50;
alter table if exists assigment add constraint FKefw2cln9qnphfmuigxokca3v0 foreign key (fk_driver) references driver;
alter table if exists assigment add constraint FKe02w83080lh2sxyx37aevwubl foreign key (fk_driver_2) references driver;
alter table if exists assigment add constraint FKjvyuhhviejcipmaooaiylteij foreign key (fk_shift) references shift;


