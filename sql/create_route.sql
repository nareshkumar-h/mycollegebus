/* */
create table route_details
(
	id int unsigned primary key auto_increment,
	route_no int not null,
	name varchar(100) not null,
	pickup_time time,
	active tinyint(1) not null default 1
);