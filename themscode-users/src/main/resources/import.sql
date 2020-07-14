insert into users (email, username, password, name, active) values ('emsedano@themscode.com', 'emsedano', '$2a$10$kWjPOoTWvo4cs0i/zBYfAu6MIhUX3ZLnHongka89y2YTEekWEFqRa', 'Elias', true );
insert into users (email, username, password, name, active) values ('john.doe@themscode.com', 'john.doe', '$2a$10$NWz5QvauLU8py4IfYlQbiOlSqBUce.zs7wZfTVUHEUUSJVvPDHb3K', 'John Doe', true );

insert into roles(name) values ('ROLE_ADMIN');
insert into roles(name) values ('ROLE_USER');

insert into users_roles(user_id, role_id) values (1, 1);
insert into users_roles(user_id, role_id) values (1, 2);
insert into users_roles(user_id, role_id) values (2, 2);


	
	