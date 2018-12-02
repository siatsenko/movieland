--------------------------------------------------------------------
insert into movie (id,name_russian,name_native,year_of_release,description,rating,price,picture_path) values (
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
insert into movie (id,name_russian,name_native,year_of_release,description,rating,price,picture_path) values (
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
insert into movie (id,name_russian,name_native,year_of_release,description,rating,price,picture_path) values (
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
insert into movie (id,name_russian,name_native,year_of_release,description,rating,price,picture_path) values (
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
insert into movie_genre (movie_id, genre_id)
select m.id as movie_id, g.id as genre_id
  from movie m
       cross join genre g
where m.name_russian = 'Побег из Шоушенка'
  and g.name in ('драма','криминал');

insert into movie_genre (movie_id, genre_id)
select m.id as movie_id, g.id as genre_id
  from movie m
       cross join genre g
where m.name_russian = 'Зеленая миля'
  and g.name in ('фэнтези','драма','криминал','детектив');

insert into movie_genre (movie_id, genre_id)
select m.id as movie_id, g.id as genre_id
  from movie m
       cross join genre g
where m.name_russian = 'Джанго освобожденный'
  and g.name in ('драма','вестерн','приключения','комедия');

insert into movie_genre (movie_id, genre_id)
select m.id as movie_id, g.id as genre_id
  from movie m
       cross join genre g
where m.name_russian = 'Танцующий с волками'
  and g.name in ('драма','приключения','вестерн');
--------------------------------------------------------------------
insert into country (id,name) values (1,'США');
insert into country (id,name) values (2,'Франция');
insert into country (id,name) values (3,'Великобритания');
insert into country (id,name) values (4,'Германия');
insert into country (id,name) values (5,'Италия');
insert into country (id,name) values (6,'Япония');
--------------------------------------------------------------------
insert into movie_country (movie_id, country_id)
select m.id as movie_id, c.id as country_id
  from movie m
       cross join country c
where m.name_russian = 'Побег из Шоушенка'
  and c.name in ('США');

insert into movie_country (movie_id, country_id)
select m.id as movie_id, c.id as country_id
  from movie m
       cross join country c
where m.name_russian = 'Зеленая миля'
  and c.name in ('США');

insert into movie_country (movie_id, country_id)
select m.id as movie_id, c.id as country_id
  from movie m
       cross join country c
where m.name_russian = 'Джанго освобожденный'
  and c.name in ('США');

insert into movie_country (movie_id, country_id)
select m.id as movie_id, c.id as country_id
  from movie m
       cross join country c
where m.name_russian = 'Танцующий с волками'
  and c.name in ('США','Великобритания');
--------------------------------------------------------------------
insert into users (id,name,email,nick) values (
1,
'Рональд Рейнольдс',
'ronald.reynolds66@example.com',
'paco');

insert into users (id,name,email,nick) values (
2,
'Дарлин Эдвардс',
'darlene.edwards15@example.com',
'bricks');

insert into users (id,name,email,nick) values (
3,
'Габриэль Джексон',
'gabriel.jackson91@example.com',
'hjkl');

insert into users (id,name,email,nick) values (
4,
'Дэрил Брайант',
'daryl.bryant94@example.com',
'exodus');

insert into users (id,name,email,nick) values (
5,
'Нил Паркер',
'neil.parker43@example.com',
'878787');

insert into users (id,name,email,nick) values (
6,
'Трэвис Райт',
'travis.wright36@example.com',
'smart');

insert into users (id,name,email,nick) values (
7,
'Амелия Кэннеди',
'amelia.kennedy58@example.com',
'beaker');

insert into users (id,name,email,nick) values (
8,
'Айда Дэвис',
'ida.davis80@example.com',
'pepsi1');

insert into users (id,name,email,nick) values (
9,
'Джесси Паттерсон',
'jessie.patterson68@example.com',
'tommy');

insert into users (id,name,email,nick) values (
10,
'Деннис Крейг',
'dennis.craig82@example.com',
'coldbeer');
--------------------------------------------------------------------
insert into review (id,movie_id,user_id,text)
select 1, m.id as movie_id, u.id as user_id, 'Фильм только выигрывает от частого просмотра и всегда поднимает мне настроение (наряду с драмой, тут еще и тонкий юмор). ' as text
  from movie m
       cross join users u
where u.name = 'Айда Дэвис'
  and m.name_russian = 'Джанго освобожденный';

insert into review (id,movie_id,user_id,text)
select 2, m.id as movie_id, u.id as user_id, 'Нетленный шедевр мирового кинематографа, который можно пересматривать десятки раз и получать все такой — же, извините за выражение, кайф от просмотра. Минусы у фильма, конечно, есть, но черт возьми. Их просто не хочется замечать! Ты настолько поглощен просмотром фильма, что просто не хочется придираться к разным мелочам. ' as text
  from movie m
       cross join users u
where u.name = 'Амелия Кэннеди'
  and m.name_russian = 'Танцующий с волками';

insert into review (id,movie_id,user_id,text)
select 3, m.id as movie_id, u.id as user_id, 'Полагаю, этот фильм должен быть в коллекции каждого уважающего себя киномана. ' as text
  from movie m
       cross join users u
where u.name = 'Трэвис Райт'
  and m.name_russian = 'Джанго освобожденный';

insert into review (id,movie_id,user_id,text)
select 4, m.id as movie_id, u.id as user_id, 'Перестал удивляться тому, что этот фильм занимает сплошь первые места во всевозможных кино рейтингах. Особенно я люблю когда при экранизации литературного произведение из него в силу специфики кинематографа исчезает ирония и появляется некий сверхреализм, обязанный удерживать зрителя у экрана каждую отдельно взятую секунду. ' as text
  from movie m
       cross join users u
where u.name = 'Рональд Рейнольдс'
  and m.name_russian = 'Зеленая миля';

insert into review (id,movie_id,user_id,text)
select 5, m.id as movie_id, u.id as user_id, 'Кино это является, безусловно, «со знаком качества». Что же до первого места в рейтинге, то, думаю, здесь имело место быть выставление «десяточек» от большинства зрителей вкупе с раздутыми восторженными откликами кинокритиков. Фильм атмосферный. Он драматичный. И, конечно, заслуживает того, чтобы находиться довольно высоко в мировом кинематографе. ' as text
  from movie m
       cross join users u
where u.name = 'Габриэль Джексон'
  and m.name_russian = 'Побег из Шоушенка';

insert into review (id,movie_id,user_id,text)
select 6, m.id as movie_id, u.id as user_id, 'Гениальное кино! Смотришь и думаешь «Так не бывает!», но позже понимаешь, что только так и должно быть. Начинаешь заново осмысливать значение фразы, которую постоянно используешь в своей жизни, «Надежда умирает последней». Ведь если ты не надеешься, то все в твоей жизни гаснет, не остается смысла. Фильм наполнен бесконечным числом правильных афоризмов. Я уверена, что буду пересматривать его сотни раз.' as text
  from movie m
       cross join users u
where u.name = 'Дарлин Эдвардс'
  and m.name_russian = 'Побег из Шоушенка';
--------------------------------------------------------------------
