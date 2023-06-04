CREATE USER if not exists 'webuser2023'@'%' identified by 'ThePassword';
grant all on *.* to 'webuser2023'@'%' with grant option;
create database if not exists OrderServiceDatabase;

CREATE USER if not exists 'webuser2023'@'%' identified by 'ThePassword';
grant all on *.* to 'webuser2023'@'%' with grant option;
create database if not exists CustomerServiceDatabase;

CREATE USER if not exists 'webuser2023'@'%' identified by 'ThePassword';
grant all on *.* to 'webuser2023'@'%' with grant option;
create database if not exists ItemServiceDatabase;