-- liquibase formatted sql

--changeset magamed:1
INSERT INTO roles VALUES
                      (1, 'ADMIN'),
                      (3, 'MANAGER'),
                      (2, 'USER')
ON CONFLICT (id) DO NOTHING;


--changeset magamed:2
ALTER TABLE users
    ALTER COLUMN password_hash TYPE VARCHAR(255);

DELETE FROM roles WHERE role = 'MANAGER'