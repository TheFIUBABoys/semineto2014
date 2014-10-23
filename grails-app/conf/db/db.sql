-- Run like this: mysql -u root -p < db.sql
create database if not exists seminario;
create user seminario@localhost identified by 'seminario';
grant all privileges on seminario.* to seminario@localhost;