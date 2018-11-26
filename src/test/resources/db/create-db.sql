CREATE TABLE v_movies
(
    id INTEGER IDENTITY PRIMARY KEY,
    name_russian VARCHAR(50),
    name_native VARCHAR(50),
    year_of_release VARCHAR(50),
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
