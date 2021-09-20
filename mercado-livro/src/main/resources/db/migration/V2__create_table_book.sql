create table book(
	id serial primary key,
	name varchar(100) not null,
	price decimal(10,2) not null,
	status varchar(100) not null,
	customer_id int not null,
	foreign key (customer_id) references customer(id)

);