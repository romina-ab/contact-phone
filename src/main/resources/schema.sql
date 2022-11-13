CREATE TABLE contact_test
(
    id         SERIAL      NOT NULL PRIMARY KEY ,
    first_name varchar(30) NOT NULL,
    last_name  varchar(30) NOT NULL,
    number     varchar(30) NOT NULL,
    email      varchar(30) NOT NULL
);
