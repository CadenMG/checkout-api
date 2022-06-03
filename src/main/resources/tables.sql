drop table item, person, cart_item;

create table if not exists item (
	id serial,
	price numeric(5, 2) not null,
	name varchar(40) not null,
	primary key(id)
);

create table if not exists person (
	id serial,
	first_name varchar(40) not null,
	last_name varchar(40) not null,
	primary key (id)
);

create table if not exists cart_item (
	person_id integer,
	item_id integer,
	quantity integer default 1,
	primary key(person_id, item_id),
	constraint fk_item foreign key(item_id) references item(id),
	constraint fk_person foreign key(person_id) references person(id)
);

create type status as enum ('shipped', 'processing')
create type shipping as enum ('standard', 'express')

create table if not exists purchased (
	person_id integer,
	item_id integer,
	quantity integer default 1,
	current_status status,
	shipping_method shipping,
	primary key(person_id, item_id),
	constraint fk_item foreign key(item_id) references item(id),
	constraint fk_person foreign key(person_id) references person(id)
);