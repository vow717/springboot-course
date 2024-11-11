create table if not exists `user` (
    id char(19) primary key ,
    name varchar(8) not null ,
    account varchar(19) not null,
    password varchar(70) not null,
    role char(4) not null ,
    create_time timestamp not null default current_timestamp,
    update_time timestamp not null default current_timestamp on update current_timestamp,
    unique (account)
);