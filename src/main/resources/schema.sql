Drop table if Exists users;

CREATE table users  (
    id UUID primary key,
    username varchar not null unique,
    password varchar not null
);