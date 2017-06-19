create database bookstore;

use bookstore;
create table user(
	uid varchar(50) primary key, 
	username varchar(50) not null,
	password varchar(50) not null,
	code vachar(50) not null,
	email varchar(50) not null
);

create table Orders(
	oid varchar(50) primary key, 
	ordertime timestamp not null,
	uid varchar(50) not null,
	total int(8) not null,
	state smallint(2) not null,
	address varchar(80)
);

create table OrderItem(
	iid varchar(50) primary key, 
	gid varchar(50) not null,
	count int(8) not null,
	oid varchar(50) not null,
	subtotal int(8) not null
);