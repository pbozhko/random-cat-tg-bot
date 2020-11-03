CREATE TABLE t_users
(
    id       SERIAL PRIMARY KEY,
    login    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    salt     VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL
);
