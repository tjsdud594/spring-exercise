create database springmember;
use springmember;

drop table if exists user CASCADE;

create table user
(
    id int(10) auto_increment,
    name varchar(255),
    email varchar(255) not null,
    primary key (id)
);

select * from user; 