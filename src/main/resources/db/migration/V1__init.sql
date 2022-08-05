create table socks
(
    id         bigserial primary key,
    color      varchar(255),
    cotton     int,
    quantity   int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into socks (color, cotton, quantity)
values ('red', 100, 1),
       ('black', 80, 2),
       ('red', 90, 3);