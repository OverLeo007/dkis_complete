CREATE DATABASE dkis;

\connect dkis

CREATE TABLE MusicalInstrument
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR,
    type     VARCHAR,
    brand    VARCHAR,
    price    NUMERIC,
    quantity INTEGER
);
