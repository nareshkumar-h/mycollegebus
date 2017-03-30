
drop table user_boarding_details;

create table user_boarding_details
(
	id int unsigned primary key auto_increment,
	user_id int unsigned not null,
	boarding_id int unsigned not null,
	active tinyint(1) not null default 1,
	created_at timestamp not null default current_timestamp,
	modified_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
	constraint user_id_fk foreign key ( user_id ) references user_accounts( id ),
	constraint boarding_id_fk foreign key ( boarding_id ) references route_boarding_details( id ),
	constraint user_boarding_id_uq unique ( user_id, boarding_id )
);

insert into user_boarding_details ( user_id, boarding_id ) values ( 1, 1 );
insert into user_boarding_details ( user_id, boarding_id ) values ( 2, 1 );
insert into user_boarding_details ( user_id, boarding_id ) values ( 3, 1 );
insert into user_boarding_details ( user_id, boarding_id ) values ( 4, 1 );
insert into user_boarding_details ( user_id, boarding_id ) values ( 5, 1 );
insert into user_boarding_details ( user_id, boarding_id ) values ( 6, 1 );

select * from user_boarding_details;
