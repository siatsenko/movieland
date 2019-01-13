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
)

CREATE VIEW v_movies AS
 SELECT m.id,
    m.name_russian,
    m.name_native,
    m.year_of_release,
    m.description,
    m.rating,
    m.price,
    m.picture_path
--     cn.country_names,
--     gn.genre_names
   FROM movie m
--      LEFT JOIN ( SELECT mg.movie_id,
--             string_agg(g.name::text, ','::text ORDER BY g.id) AS genre_names
--            FROM movie_genre mg
--              JOIN genre g ON mg.genre_id = g.id
--           GROUP BY mg.movie_id) gn ON m.id = gn.movie_id
--      LEFT JOIN ( SELECT mc.movie_id,
--             string_agg(c.name::text, ','::text ORDER BY c.id) AS country_names
--            FROM movie_country mc
--              JOIN country c ON mc.country_id = c.id
--           GROUP BY mc.movie_id) cn ON m.id = cn.movie_id;
;
--------------------------------------------------------------------
