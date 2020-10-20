ALTER TABLE t_photos
    ADD checksum TEXT;

UPDATE t_photos
SET checksum = md5(t_photos.content);

ALTER TABLE t_photos
    ALTER COLUMN checksum SET NOT NULL;
