use CustomerServiceDatabase;

create table customer (id int not null, name varchar(30),personnummer varchar(30),primary key (id)) engine=InnoDB;
create table customer_seq (next_val bigint) engine=InnoDB;
insert into customer_seq values (1);

use ItemServiceDatabase;

create table items (id int not null,name varchar(30),pris double,primary key (id)) engine=InnoDB;
create table items_seq (next_val bigint) engine=InnoDB;
insert into items_seq values (1);

use OrderServiceDatabase;

create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values (1);

create table items (id int not null,name varchar(30),pris double,primary key (id)) engine=InnoDB;
create table items_seq (next_val bigint) engine=InnoDB;
insert into items_seq values (1);

create table orders (id bigint not null,customer_id bigint,order_date date,primary key (id)) engine=InnoDB;
create table orders_items (order_id bigint not null,item_id int not null) engine=InnoDB;
create table orders_seq (next_val bigint) engine=InnoDB;
insert into orders_seq values (1);


