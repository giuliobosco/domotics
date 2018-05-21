CREATE DATABASE db_domotics;

CREATE TABLE user (
	id INT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(50) UNICODE NOT NULL ,
	password VARCHAR(50) NOT NULL,
	name VARCHAR(30) NOT NULL ,
	surname VARCHAR(30) NOT NULL ,
	email VARCHAR(80) NOT NULL
);

CREATE TABLE group (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(30),
	description VARCHAR(30)
);

CREATE TABLE user_appartain (
	user_id INT,
	group_id INT,
	PRIMARY KEY (user_id, group_id),
	FOREIGN KEY (user_id) REFERENCES user(id),
	FOREIGN KEY (group_id) REFERENCES group(id)
);

CREATE TABLE room (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20)
);

CREATE TABLE group_control