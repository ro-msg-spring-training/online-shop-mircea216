INSERT INTO location(name, country, city, county, street_address)
VALUES ('Emag', 'Romania', 'Cluj Napoca', 'Cluj', 'Dorobantilor'),
       ('Altex', 'Romania', 'Oradea', 'Bihor', 'Paleu'),
       ('CCC', 'Romania', 'Timisoara', 'Timisoara', 'Bd Principal');
INSERT INTO customer(first_name, last_name, username, password, email_address)
VALUES ('Alex', 'Doe', 'alexdoe', 'password', 'adoe@gmail.com');
INSERT INTO stock(product, location, quantity)
VALUES (1, 1, 15),
       (1, 2, 20),
       (1, 3, 10);