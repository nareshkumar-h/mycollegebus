
drop table roles;
create table roles(
	id int unsigned primary key auto_increment,
	name varchar(10) not null,
	active tinyint(1) not null default 1,
	created_at timestamp not null default current_timestamp,
	modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
	unique (name)
);

insert into roles ( name ) values ('Admin');
insert into roles ( name ) values ('Faculty');
insert into roles ( name ) values ('Student');

drop table user_accounts;
create table user_accounts
(
	id int unsigned primary key auto_increment,
	name varchar(50) not null,
	email varchar(50) not null,
	password varchar(30) not null,
	mobile_no bigint not null,
	verified tinyint(1) not null default 0,
	active tinyint(1) not null default 1,
	role_id int default 3,
	unique(email),
	created_at timestamp not null default current_timestamp,
	modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP 
);

insert into user_accounts ( name, email, password, mobile_no, role_id ) values ( 'Akshay', 'ashpeekay23@gmail.com', 'pass123', 7777777777 , 3 );
insert into user_accounts ( name, email, password, mobile_no, role_id ) values ( 'Naresh', 'nareshkumarh@live.com', 'pass123', 9994194773 , 3 );
insert into user_accounts ( name, email, password, mobile_no, role_id ) values ( 'Janani', 'jananise@gmail.com', 'pass123', 7777777777 , 3 );
insert into user_accounts ( name, email, password, mobile_no, role_id ) values ( 'Sai', 'sai@gmail.com', 'pass123', 7777777777 , 3 );
insert into user_accounts ( name, email, password, mobile_no, role_id ) values ( 'Senthamil', 'senthamil@gmail.com', 'pass123', 7777777777 , 3 );
insert into user_accounts ( name, email, password, mobile_no, role_id ) values ( 'Surya', 'surya@gmail.com', 'pass123', 7777777777 , 3 );

select * from user_accounts;
