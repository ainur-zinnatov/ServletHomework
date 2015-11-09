# ServletHomework
Servlet_5
CREATE TABLE posts
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    text VARCHAR(50),
    user_id INT NOT NULL,
    date DATETIME DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE users
(
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    sex INT,
    subscription INT,
    name VARCHAR(100) NOT NULL,
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT
);
ALTER TABLE posts ADD FOREIGN KEY (user_id) REFERENCES users (id);
CREATE UNIQUE INDEX unique_email ON users (email);
