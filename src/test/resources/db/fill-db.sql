--------------------------------------------------------------------
insert into v_movies (id,name_russian,name_native,year_of_release,description,rating,price,picture_path) values (
1,
'Побег из Шоушенка',
'The Shawshank Redemption',
'1994',
'Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.',
8.9,
123.45,
'https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg'
);
--------------------------------------------------------------------
insert into v_movies (id,name_russian,name_native,year_of_release,description,rating,price,picture_path) values (
2,
'Зеленая миля',
'The Green Mile',
'1999',
'Обвиненный в страшном преступлении, Джон Коффи оказывается в блоке смертников тюрьмы «Холодная гора». Вновь прибывший обладал поразительным ростом и был пугающе спокоен, что, впрочем, никак не влияло на отношение к нему начальника блока Пола Эджкомба, привыкшего исполнять приговор.',
8.9,
134.67,
'https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1._SY209_CR0,0,140,209_.jpg'
);
--------------------------------------------------------------------
insert into v_movies (id,name_russian,name_native,year_of_release,description,rating,price,picture_path) values (
3,
'Джанго освобожденный',
'Django Unchained',
'2012',
'Эксцентричный охотник за головами, также известный как «Дантист», промышляет отстрелом самых опасных преступников. Работенка пыльная, и без надежного помощника ему не обойтись. Но как найти такого и желательно не очень дорогого? Беглый раб по имени Джанго — прекрасная кандидатура. Правда, у нового помощника свои мотивы — кое с чем надо разобраться…',
8.5,
170.00,
'https://images-na.ssl-images-amazon.com/images/M/MV5BMjIyNTQ5NjQ1OV5BMl5BanBnXkFtZTcwODg1MDU4OA@@._V1._SY209_CR0,0,140,209_.jpg'
);
--------------------------------------------------------------------
insert into v_movies (id,name_russian,name_native,year_of_release,description,rating,price,picture_path) values (
4,
'Танцующий с волками',
'Dances with Wolves',
'1990',
'Действие фильма происходит в США во времена Гражданской войны. Лейтенант американской армии Джон Данбар после ранения в бою просит перевести его на новое место службы ближе к западной границе США. Место его службы отдалённый маленький форт. Непосредственный его командир покончил жизнь самоубийством, а попутчик Данбара погиб в стычке с индейцами после того, как довез героя до места назначения. Людей, знающих, что Данбар остался один в форте и должен выжить в условиях суровой природы, и в соседстве с кажущимися негостеприимными коренными обитателями Северной Америки, просто не осталось. Казалось, он покинут всеми. Постепенно лейтенант осваивается, он ведет записи в дневнике…',
8.00,
120.55,
'https://images-na.ssl-images-amazon.com/images/M/MV5BMTY3OTI5NDczN15BMl5BanBnXkFtZTcwNDA0NDY3Mw@@._V1._SX140_CR0,0,140,209_.jpg'
);
--------------------------------------------------------------------
insert into genre (id,name) values (1,'драма');
insert into genre (id,name) values (2,'криминал');
insert into genre (id,name) values (3,'фэнтези');
insert into genre (id,name) values (4,'детектив');
insert into genre (id,name) values (5,'мелодрама');
insert into genre (id,name) values (6,'биография');
insert into genre (id,name) values (7,'комедия');
insert into genre (id,name) values (8,'фантастика');
insert into genre (id,name) values (9,'боевик');
insert into genre (id,name) values (10,'триллер');
insert into genre (id,name) values (11,'приключения');
insert into genre (id,name) values (12,'аниме');
insert into genre (id,name) values (13,'мультфильм');
insert into genre (id,name) values (14,'семейный');
insert into genre (id,name) values (15,'вестерн');
--------------------------------------------------------------------
insert into movie_genre (movie_id,genre_id) values (1,	1);
insert into movie_genre (movie_id,genre_id) values (1,	2);
insert into movie_genre (movie_id,genre_id) values (2,	1);
insert into movie_genre (movie_id,genre_id) values (2,	2);
insert into movie_genre (movie_id,genre_id) values (2,	3);
insert into movie_genre (movie_id,genre_id) values (2,	4);
insert into movie_genre (movie_id,genre_id) values (3,	1);
insert into movie_genre (movie_id,genre_id) values (3,	7);
insert into movie_genre (movie_id,genre_id) values (3,	11);
insert into movie_genre (movie_id,genre_id) values (3,	15);
insert into movie_genre (movie_id,genre_id) values (4,	1);
insert into movie_genre (movie_id,genre_id) values (4,	11);
insert into movie_genre (movie_id,genre_id) values (4,	15);
--------------------------------------------------------------------

