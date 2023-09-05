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
