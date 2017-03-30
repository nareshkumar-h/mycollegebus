create table routes
(
	id int unsigned primary key auto_increment,	
	name varchar(100) not null,	
	active tinyint(1) not null default 1,
	trip char(1) not null
);



create table route_boarding_details
(
	id int unsigned primary key auto_increment,
	route_id int not null,
	name varchar(100) not null,
	pickup_time time,
	active tinyint(1) not null default 1,
	constraint route_id_fk foreign key (  route_id ) references routes( id )
);
