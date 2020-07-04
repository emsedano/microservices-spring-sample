insert into users (email, username, password, name, active) values ('emsedano@themscode.com', 'emsedano', 'holo', 'Elias', true );
insert into users (email, username, password, name, active) values ('john.doe@themscode.com', 'john.doe', 'john', 'John Doe', true );

insert into roles(name) values ('ROLE_ADMIN');
insert into roles(name) values ('ROLE_USER');

insert into users_roles(user_id, role_id) values (1, 1);
insert into users_roles(user_id, role_id) values (1, 2);
insert into users_roles(user_id, role_id) values (2, 2);


	
	