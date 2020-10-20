ALTER TABLE t_photos
    ADD enabled BOOLEAN;

UPDATE t_photos
SET enabled = true;

ALTER TABLE t_photos
    ALTER COLUMN enabled SET NOT NULL;
