drop table if exists employee cascade
create table employee (id int8 generated by default as identity, first_name varchar(255), last_name varchar(255), phone varchar(255),email varchar(255), primary key (id))
alter table if exists employee add constraint UK_email unique (email)
