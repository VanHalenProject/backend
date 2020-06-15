Drop table if Exists users;
Drop table if Exists skittles;

CREATE table users  (
    id UUID primary key,
    username varchar not null unique,
    password varchar not null
);

CREATE table skittles  (
    id UUID primary key,
    red int,
    orange int,
    yellow int,
    green int,
    purple int
);