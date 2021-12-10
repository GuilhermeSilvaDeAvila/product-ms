create table product(
	id varchar(10) not null auto_increment,
	name varchar(60) not null,
	description varchar(300) not null,
	price decimal(10,2) not null,

	primary key (id)
);

INSERT INTO product(name, description, price) VALUES('Product Test 1', 'Produto criado para teste', 100.00);
INSERT INTO product(name, description, price) VALUES('Product Test 2', 'Produto criado para teste', 100.00);
INSERT INTO product(name, description, price) VALUES('Product Test 3', 'Produto criado para teste', 55);
INSERT INTO product(name, description, price) VALUES('Product Test 4', 'Produto criado para teste', 50);