CREATE TABLE movie
(
    id INTEGER IDENTITY PRIMARY KEY,
    name_russian VARCHAR(50),
    name_native VARCHAR(50),
    year_of_release VARCHAR(50),
    description VARCHAR(1000),
    rating DECIMAL(10,1),
    price DECIMAL(10,2),
    picture_path VARCHAR(1000)
);

CREATE TABLE genre
(
    id INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE movie_genre
(
    movie_id INTEGER,
    genre_id INTEGER
);

CREATE TABLE users
(
    id INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(50),
    role  VARCHAR(50)
);

CREATE TABLE country
(
    id INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE movie_country
(
    movie_id INTEGER,
    country_id INTEGER
);

CREATE TABLE review
(
  id INTEGER IDENTITY PRIMARY KEY,
  movie_id INTEGER,
  user_id INTEGER,
  text VARCHAR(1000)
);
--------------------------------------------------------------------
