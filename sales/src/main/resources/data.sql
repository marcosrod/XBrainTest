
CREATE TABLE seller
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL
);

INSERT INTO seller (name)
VALUES ('João Aparecido Santos'),
       ('Joseph Moura Carmon'),
       ('Tsukihiro Omar Braga');


CREATE TABLE sale
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    date  DATE NOT NULL,
    value DOUBLE NOT NULL,
    seller_Id INT NOT NULL,
    seller_Name VARCHAR(30) NOT NULL

);

INSERT INTO sale (date, value, seller_Id, seller_Name)
VALUES ('2021-11-06',150,1,'João Aparecido Santos'),
       ('2021-11-06',300,1,'João Aparecido Santos'),
       ('2021-11-07',322,1,'João Aparecido Santos'),
       ('2021-11-08',120,1,'João Aparecido Santos'),
       ('2021-11-06',150,2,'Joseph Moura Carmon'),
       ('2021-11-07',175,2,'Joseph Moura Carmon'),
       ('2021-11-08',226,2,'Joseph Moura Carmon'),
       ('2021-11-08',365,2,'Joseph Moura Carmon'),
       ('2021-11-06',600,3,'Tsukihiro Omar Braga'),
       ('2021-11-06',95,3,'Tsukihiro Omar Braga'),
       ('2021-11-07',125,3,'Tsukihiro Omar Braga'),
       ('2021-11-07',300,3,'Tsukihiro Omar Braga');



