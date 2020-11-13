INSERT INTO role(role) VALUES ('ADMIN');
INSERT INTO role(role) VALUES ('COUNCILMAN');
INSERT INTO councilman (first_name, last_name, committee, description, email, password, role_id) VALUES ('Tomasz', 'Tomalski', 'Oświaty', 'Radny 1 kadencji', 'tomasz@tomasz', 'haslo1', 2);
INSERT INTO councilman (first_name, last_name, committee, description, email, password, role_id) VALUES ('Bartosz', 'Bartoszczyk', 'Budżetu', 'Radny 8 kadencji', 'bartosz@bartosz.pl', 'haslo2', 2);
INSERT INTO super_admin(email, password) VALUES ('admin@gmina.pl', 'haslo3');
INSERT INTO interpellation(title, description, date, councilman_id) VALUES ('Droga Budziwojow', 'Proszę o załatanie dziur w drodze gminnej. Kałuże jak cholera', '2020-11-11', 1);
INSERT INTO interpellation(title, description, date, councilman_id) VALUES ('Śmieci', 'Proszę uprzątnąć dzikie wysypisko', '2020-11-11', 1);
INSERT INTO interpellation(title, description, date, councilman_id) VALUES ('Plac zabaw', 'Proszę o ujęcie w planie budżetu budowę placu zabaw w miejscowości Budziwojów', '2020-11-11', 2);
