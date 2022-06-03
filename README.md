# Checkout API
Simple checkout api - meant to replace an existing Monolith architecture with a secure and
highly scalable alternative - with Spring Boot. 
Handles adding and deleting items of variable quantities to a cart, and preparing/cleaning-up
after checkout. Written in such a way to be highly secure, maintainable, and scalable.

## API
### Checkout Controller
- `api/v1/checkout/{personId}`
  - `POST: check's out the person, prepares items for shipment`
### Cart Controller
- `api/v1/cart/{personId}`
  - `GET: returns the state of the person's cart`
  - `DELETE: deletes some amount of an item from the person's cart`
  - `POST: adds some amount of an item to the person's cart`

## Model
### Person
```sql
create table if not exists person (
  id uuid,
  first_name varchar(40) not null,
  last_name varchar(40) not null,
  primary key (id)
);
```
### Item
```sql
create table if not exists item (
  id serial,
  price numeric(5, 2) not null,
  name varchar(40) not null,
  primary key(id)
);
```
### Cart Item
```sql
create table if not exists cart_item (
  person_id uuid,
  item_id integer,
  quantity integer default 1,
  primary key(person_id, item_id),
  constraint fk_item foreign key(item_id) references item(id),
  constraint fk_person foreign key(person_id) references person(id)
);
```
### Purchased
```sql
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
```

## Security
- Service that generates JWTs on login
  - `POST: api/v1/authenticate` with login info and returns JWT with expiration
  - store on client in an httpOnly cookie 
- FE passes JWT in HTTP Authorization header
- Pre-request Authentication step that confirms the user is who say they are via the JWT
- Authentication implies Authorization (in this very simple API)
- Using uuid for the person id type prevents hacker from guessing valid person ids


## Scaling
- Latency issue: test load on service vs on db
- Run the service on multiple ports (or machines...)
- Sit a load balancer in front of incoming traffic to evenly distribute the work and prevent bottleneck


## Next Steps:
- logging and error handling
- handling payments


## Setup/Requirements
- Java 18
- Gradle 7.4.2 or higher
- Postgres 14 or higher
- Add DB properties to `application.properties`
- optionally run `resources/data.sql` for some test data