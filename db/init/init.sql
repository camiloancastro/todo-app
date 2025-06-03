create table task (
    id serial not null,
    title varchar(255),
    description varchar(255),
    completed boolean,
    primary key (id)
);
