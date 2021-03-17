create table persons (
	person_id serial primary key,
	first_name text,
	last_name text,
	parent_id int
);

create table parents(
	parent_id serial primary key,
	person_id int
);

insert into persons(first_name, last_name) values('chris', 'proutt')

alter table persons drop parent_id;

alter table persons
add parent_first_name text;

alter table persons
add parent_last_name text;

alter table parents
add relationship text;

alter table parents
add parent_id int;


