DROP TABLE people IF EXISTS;

CREATE TABLE people  (
    person_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(20)
);

INSERT INTO people (first_name, last_name) values ('Carlos', 'Xavier');
INSERT INTO people (first_name, last_name) values ('Gioconda Brasil', 'Xavier');
INSERT INTO people (first_name, last_name) values ('Bolos', 'Cavalcante');
