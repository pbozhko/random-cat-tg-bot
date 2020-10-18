CREATE TABLE t_images
(
    id         SERIAL       PRIMARY KEY,
    title      VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    content    BYTEA        NOT NULL,
    width      INTEGER      NOT NULL,
    height     INTEGER      NOT NULL,
    mime_type  VARCHAR(20)  NOT NULL
);
