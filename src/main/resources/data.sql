drop table if exists alert;

create table alert
(
    id     int auto_increment primary key,
    title  varchar(255) not null,
    target varchar(255) not null
);
