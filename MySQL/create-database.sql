CREATE USER 'webuser2023'@'localhost' identified by 'ThePassword';
grant all on *.* to 'webuser2023'@'localhost' with grant option;
create database OrderServiceDatabase;

CREATE USER 'webuser2023'@'localhost' identified by 'ThePassword';
grant all on *.* to 'webuser2023'@'localhost' with grant option;
create database CustomerServiceDatabase;

CREATE USER 'webuser2023'@'localhost' identified by 'ThePassword';
grant all on *.* to 'webuser2023'@'localhost' with grant option;
create database ItemServiceDatabase;