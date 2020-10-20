ALTER TABLE t_photos
    ADD uuid UUID;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

UPDATE t_photos
SET uuid = uuid_generate_v4();

ALTER TABLE t_photos
    ADD UNIQUE (uuid);

ALTER TABLE t_photos
    ALTER COLUMN uuid SET NOT NULL;
