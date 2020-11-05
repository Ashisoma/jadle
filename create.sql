SET MODE PostgreSQL;



\c jadle

CREATE TABLE IF NOT EXISTS restaurants (
 id SERIAL PRIMARY KEY,
 name VARCHAR,
 address VARCHAR,
 zipcode VARCHAR,
 phone VARCHAR,
 website VARCHAR,
 email VARCHAR
);

CREATE TABLE IF NOT EXISTS foodtypes (
 id SERIAL PRIMARY KEY ,
 name VARCHAR
);

CREATE TABLE IF NOT EXISTS reviews (
 id SERIAL PRIMARY KEY,
 writtenBy VARCHAR,
 content VARCHAR,
 rating VARCHAR,
 restaurantId INTEGER
);

CREATE TABLE IF NOT EXISTS restaurants_foodtypes(
 id SERIAL PRIMARY KEY,
 foodtypesId INTEGER,
 restaurantId INTEGER
);