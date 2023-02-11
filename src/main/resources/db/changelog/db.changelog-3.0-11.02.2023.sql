--liquibase formatted sql

--changeset afedyakov:1
ALTER TABLE users
ADD COLUMN image VARCHAR(64);

--changeset afedyakov:2
ALTER TABLE users_aud
ADD COLUMN image VARCHAR(64);