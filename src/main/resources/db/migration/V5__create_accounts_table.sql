CREATE TABLE t_accounts
(
    id             SERIAL        PRIMARY KEY,
    account_id     BIGINT        NULL,
    first_name     VARCHAR(255)  NULL,
    last_name      VARCHAR(255)  NULL,
    is_bot         VARCHAR(255)  NULL,
    user_name      VARCHAR(255)  NULL,
    language_code  VARCHAR(255)  NULL
);
