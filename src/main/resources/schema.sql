create table if not exists `user`(
    id char(19) primary key,
    name varchar(20) not null,
    updated_time datetime not null default current_timestamp on update current_timestamp,
    created_time datetime not null default current_timestamp
);

create table if not exists `address`(
    id char(19) primary key,
    name varchar(20) not null,
    user_id char(19) not null,
    updated_time datetime not null default current_timestamp on update current_timestamp,
    created_time datetime not null default current_timestamp,

    index (user_id)
);