create user 'Jen'@'localhost'

create database tasks;
grant select, insert, delete, create, update, drop on tasks.* to 'jen'@'localhost';