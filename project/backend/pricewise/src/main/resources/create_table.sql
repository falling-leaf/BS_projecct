use pricewise;

create table if not exists User (
    id int primary key auto_increment,
    account varchar(128) not null unique,
    password varchar(128) not null,
    email varchar(128) not null unique
    );

create table if not exists History (
    id int primary key auto_increment,
    account varchar(128) not null,
    search_input varchar(128) not null,
    search_time DATETIME not null
);