delete from reserved_seat;
delete from reservation;
delete from show;
delete from movie;
delete from auditorium;
delete from ticket_type;
delete from application_user;

insert into movie (id, title, genre, age_limit, duration, release_year, country, description, image_name) values (1, 'Skazani na Shawshank', 'DRAMA', 'MIN_15', 142, 1994, 'USA', 'Film nakręcony na podstawie książki mistrza horrorów Stephena Kinga. Andy Dufresne (Tim Robbins), dobrze zarabiający bankier z Nowej Anglii, zostaje oskarżony o podwójne zabójstwo - swojej żony i jej kochanka. Uparcie twierdzi, że jest niewinny, ale dzięki niezbitym dowodom zostaje skazany na podwójne dożywocie w więzieniu Shawshank. Shawshank rządzi hipokryta i fanatyk biblijny, naczelnik Norton (Bob Gunton), a wraz z nim sadystyczni strażnicy. Andy już po kilku dniach poznaje brutalną, więzienną rzeczywistość, ale dzięki wrodzonej inteligencji, sprytowi oraz pomocy przyjaciela Ellisa Boyda "Reda" Reddinga (Morgan Freeman) udaje mu się zachować nadzieję, która pozwoli dokonać zemsty.', 'skazani_na_shawshank.jpg');
insert into movie (id, title, genre, age_limit, duration, release_year, country, description, image_name) values (2, 'Zielona mila', 'DRAMA', 'MIN_15', 188, 1999, 'USA', 'Rok 1935. Paul Edgecombe (Tom Hanks) jest jednym ze strażników bloku śmierci w więzieniu Cold Mountain. Do jego obowiązków należy odprowadzanie skazańców do celi śmierci długim korytarzem, wyłożonym zieloną wykładziną, zwaną "Zieloną milą". Pewnego dnia do więzienia przybywa olbrzymi czarnoskóry skazaniec, John Coffey (Michael Clarke Duncan). Ma oczekiwać na karę śmierci za zamordowanie dwóch białych dziewczynek. Jego wizyta na zawsze zmieni życie Paula i pozostałych pracowników więzienia.', 'zielona_mila.jpg');
insert into movie (id, title, genre, age_limit, duration, release_year, country, description, image_name) values (3, 'Pulp Fiction', 'CRIME', 'MIN_18', 154, 1994, 'USA', 'Płatni mordercy, Jules (Samuel L. Jackson) i Vincent (John Travolta), dostają zlecenie, by odzyskać z rąk przypadkowych rabusiów tajemniczą walizkę bossa mafii. Nie dość tego, Vincent dostaje kolejną robotę - na czas nieobecności gangstera w mieście ma zaopiekować się jego poszukującą wrażeń żoną Mią (Uma Thurman). Vincent i Jules niespodziewanie wpadają po uszy, gdy przypadkowo zabijają zakładnika. Kłopoty ma też podupadły bokser (Bruce Willis), który otrzymał dużą sumę za przegranie swojej walki. Walkę jednak wygrywa, a Los Angeles staje się od tej chwili dla niego za małe. Specjaliści od mokrej roboty będą mieli co robić...', 'pulp_fiction.jpg');
insert into movie (id, title, genre, age_limit, duration, release_year, country, description, image_name) values (4, 'Milczenie owiec', 'THRILLER', 'MIN_15', 118, 1991, 'USA', 'Agentce FBI, Clarise Starling (Jodie Foster), zostaje powierzona sprawa "Buffalo Billa", seryjnego mordercy wyróżniającego się szczególnym okrucieństwem wobec swych ofiar, które odziera ze skóry. Sprawę pomaga jej rozwikłać inny, niezwykle inteligentny i niebezpieczny morderca - Hannibal Lecter (Anthony Hopkins) - pacjent więziennego centrum psychiatrycznego. Hannibal jest zauroczony Clarice, a w zamian za odkrywanie przez nią części swej przeszłości, kawałek po kawałku odkrywa dla niej tajemnicę morderstw.', 'milczenie_owiec.jpg');
insert into movie (id, title, genre, age_limit, duration, release_year, country, description, image_name) values (5, 'Dwunastu gniewnych ludzi', 'DRAMA', 'MIN_15', 96, 1957, 'USA', 'Dwunastu przysięgłych w procesie o morderstwo rozpatruje kwestię winy. Wszyscy poza jednym są pewni, że oskarżony zamordował swego ojca. Jednak wyrok musi być jednomyślny. Rozpętuje się gorąca dyskusja, w toku której wątpiący przekonuje innych do swego zdania.', 'dwunastu_gniewnych_ludzi.jpg');
insert into movie (id, title, genre, age_limit, duration, release_year, country, description, image_name) values (6, 'Forrest Gump', 'COMEDY', 'MIN_12', 142, 1994, 'USA', '"Forrest Gump" to romantyczna historia, w której Tom Hanks wcielił się w tytułową postać - nierozgarniętego młodego człowieka o wielkim sercu i zdolności do odnajdywania się w największych wydarzeniach w historii USA, począwszy od swego dzieciństwa w latach 50-tych. Po tym, jak staje się gwiazdą footballu, odznaczonym bohaterem wojennym i odnoszącym sukcesy biznesmenem, główny bohater zyskuje status osobistości, lecz nigdy nie rezygnuje z poszukiwania tego, co dla niego najważniejsze - miłości swej przyjaciółki, Jenny Curran.', 'forrest_gump.jpg');
insert into movie (id, title, genre, age_limit, duration, release_year, country, description, image_name) values (7, 'Nietykalni', 'BIOGRAPHY', 'MIN_12', 112, 2011, 'FRANCE', 'Ta historia zdarzyła się naprawdę. Sparaliżowany na skutek wypadku milioner zatrudnia do pomocy i opieki młodego chłopaka z przedmieścia, który właśnie wyszedł z więzienia. Zderzenie dwóch skrajnie różnych światów daje początek szeregowi niewiarygodnych przygód i przyjaźni, która czyni ich... nietykalnymi.', 'nietykalni.jpg');
insert into movie (id, title, genre, age_limit, duration, release_year, country, description, image_name) values (8, 'Pianista', 'BIOGRAPHY', 'MIN_12', 150, 2002, 'POLAND', 'Ekranizacja wspomnień Władysława Szpilmana. W roli głównej Adrien Brody ("Cienka czerwona linia", "Mordercze lato").  Władysław Szpilman, wybitny polski pianista żydowskiego pochodzenia żyje w warszawskim getcie. Dzieli tam okrutny los i upokorzenie swojego narodu. Przed ostateczną "wywózką" mieszkańców getta do obozów zagłady udaje mu się stamtąd uciec. Ukrywa się samotnie w ruinach Warszawy. Niespodziewanie to przyjaźń z niemieckim oficerem pozwoli mu przetrwać najcięższy okres... ', 'pianista.jpg');
insert into movie (id, title, genre, age_limit, duration, release_year, country, description, image_name) values (9, 'Król Lew', 'ANIMATION', 'B_O', 89, 1994, 'USA', 'Film opowiada o losach Simby, młodego lwa, który - oskarżony o nieumyślne zabójstwo swojego ojca, króla Lwiej Skały - zostaje skazany na wygnanie. Władzę w królestwie obejmuje Skaza, brat zmarłego władcy. Mija kilka lat. Pewnego dnia Simba spotyka dawną przyjaciółkę, od której dowiaduje się, że królestwo chyli się ku upadkowi. Młody następca tronu postanawia powrócić do dawnej ojczyzny i odzyskać zabrany mu podstępem tron.', 'krol_lew.jpg');
insert into movie (id, title, genre, age_limit, duration, release_year, country, description, image_name) values (10, 'Slumdog. Milioner z ulicy', 'DRAMA', 'MIN_12', 120, 2008, 'GREAT_BRITAIN', 'Jamal, mieszkaniec slumsów Bombaju, w wieku 18 lat bierze udział w hinduskiej wersji "Milionerów". Od finałowej wygranej dzieli go ostatnie pytanie, jednak zanim zdąży na nie odpowiedzieć, aresztuje go policja. Funkcjonariusze chcą się dowiedzieć jak chłopak może wiedzieć tak dużo. Jamal opowiada prawdziwą historię dzieciństwa, przemocy i miłości swojego życia. To ulica była jego szkołą. Szkołą przetrwania...', 'slumdog.jpg');

insert into auditorium(id, name, rows, columns) values (1, 'A', 9, 18);
insert into auditorium(id, name, rows, columns) values (2, 'B', 8, 22);
insert into auditorium(id, name, rows, columns) values (3, 'C', 8, 18);
insert into auditorium(id, name, rows, columns) values (4, 'D', 10, 12);
insert into auditorium(id, name, rows, columns) values (5, 'E', 14, 12);

insert into show (id, movie_id, auditorium_id, show_date, show_time) values (1, 1, 1, CURRENT_DATE, '11:30');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (2, 1, 2, CURRENT_DATE, '20:00');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (3, 1, 3, CURRENT_DATE + integer '1', '12:00');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (4, 1, 4, CURRENT_DATE + integer '1', '15:00');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (5, 2, 5, CURRENT_DATE, '12:00');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (6, 2, 2, CURRENT_DATE, '21:00');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (7, 2, 3, CURRENT_DATE + integer '1', '14:00');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (8, 2, 5, CURRENT_DATE + integer '2', '10:30');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (9, 3, 4, CURRENT_DATE, '12:00');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (10, 3, 2, CURRENT_DATE + integer '3', '21:00');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (11, 4, 3, CURRENT_DATE, '12:00');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (12, 4, 1, CURRENT_DATE + integer '1', '13:00');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (13, 4, 2, CURRENT_DATE + integer '6', '10:30');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (14, 5, 3, CURRENT_DATE, '13:00');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (15, 5, 4, CURRENT_DATE + integer '1', '20:15');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (16, 5, 5, CURRENT_DATE + integer '5', '12:15');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (17, 6, 5, CURRENT_DATE + integer '2', '15:30');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (18, 6, 3, CURRENT_DATE + integer '3', '18:30');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (19, 7, 2, CURRENT_DATE + integer '2', '15:30');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (20, 7, 3, CURRENT_DATE + integer '4', '17:30');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (21, 8, 4, CURRENT_DATE + integer '1', '12:15');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (22, 8, 1, CURRENT_DATE + integer '1', '16:15');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (23, 8, 1, CURRENT_DATE + integer '1', '19:15');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (24, 9, 2, CURRENT_DATE + integer '4', '15:30');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (25, 9, 5, CURRENT_DATE + integer '4', '19:00');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (26, 10, 4, CURRENT_DATE, '13:20');
insert into show (id, movie_id, auditorium_id, show_date, show_time) values (27, 10, 3, CURRENT_DATE + integer '2', '13:20');

insert into reservation(id, show_id, timestamp, first_name, last_name, phone_number) values (101, 1, CURRENT_TIMESTAMP, 'Jan', 'Nowak', '111111111');

insert into reserved_seat(id, show_id, seat, reservation_id) values (101, 3, 'A1', 101);
insert into reserved_seat(id, show_id, seat, reservation_id) values (102, 3, 'A4', 101);
insert into reserved_seat(id, show_id, seat, reservation_id) values (103, 3, 'A7', 101);
insert into reserved_seat(id, show_id, seat, reservation_id) values (104, 3, 'A10', 101);
insert into reserved_seat(id, show_id, seat, reservation_id) values (105, 3, 'A13', 101);
insert into reserved_seat(id, show_id, seat, reservation_id) values (106, 3, 'A17', 101);

insert into ticket_type(id, category, price) values (101, 'NORMALNY', 21);
insert into ticket_type(id, category, price) values (102, 'ULGOWY', 14);

insert into application_user(username, password) values ('test', '$2a$04$EQhY1U0MdX.BetO6zstvtOTHKYfffI2DfWHbK.VPvmpeeLROP7C0i');
