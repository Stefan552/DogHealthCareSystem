create database UserDB;

use UserDB;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (username, password) VALUES
('user1', 'password1');

INSERT INTO users (username, password) VALUES
('user2', 'password2');

INSERT INTO users (username, password) VALUES
('user3', 'password3');

select* from clients ;
 
 
 ALTER TABLE Clients
ADD vaccinated varchar(15),
ADD age INT,
ADD veterinary_healthpoint VARCHAR(255);

DELETE FROM clients;


INSERT INTO Clients ( vaccinated, age, veterinary_healthpoint)
VALUES
    ('Yes with C876', 5, 'New York Vet Clinic'),
    ('NO', 3, 'LA Pet Hospital'),
    ('Yes with C879', 4,'Chicago Vet Center'),
    ('Yes with A455', 2, 'Houston Animal Hospital');


select* from clients;
Delete from clients where user_id=1;

CREATE TABLE Clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    phoneNumber VARCHAR(15),
    city VARCHAR(255),
    petName VARCHAR(255),
    breed VARCHAR(255),
    vaccinated VARCHAR(15), 
    age INT,
    veterinary_healthpoint VARCHAR(255)
);

drop table clients;
use UserDB;

INSERT INTO Clients (firstname, lastname, phoneNumber, city, petName, breed, vaccinated, age, veterinary_healthpoint)
VALUES
    ('John', 'Doe', '123-456-7890', 'New York', 'Fluffy', 'Golden Retriever', 'Yes', 5, 'New York Vet Clinic'),
    ('Alice', 'Smith', '987-654-3210', 'Los Angeles', 'Buddy', 'Labrador Retriever', 'No', 3, 'LA Pet Hospital'),
    ('David', 'Johnson', '555-123-4567', 'Chicago', 'Max', 'German Shepherd', 'Yes', 4, 'Chicago Vet Center'),
    ('Sarah', 'Williams', '888-777-1234', 'Houston', 'Luna', 'Husky', 'No', 2, 'Houston Animal Hospital');



SELECT* FROM clients;