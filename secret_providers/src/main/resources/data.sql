insert into role (id, role) values (1,'VIEW_ADMIN') ON CONFLICT DO NOTHING/UPDATE;
insert into role (id, role) values (2,'VIEW_USERS') ON CONFLICT DO NOTHING/UPDATE;
