CREATE DATABASE db_domotics;

CREATE TABLE user (
	id       INT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(50) UNICODE NOT NULL,
	password VARCHAR(50)         NOT NULL,
	name     VARCHAR(30)         NOT NULL,
	surname  VARCHAR(30)         NOT NULL,
	email    VARCHAR(80)         NOT NULL
);

CREATE TABLE group (
	id          INT PRIMARY KEY AUTO_INCREMENT,
	name        VARCHAR(30),
	description VARCHAR(30)
);

CREATE TABLE user_appartain (
	user_id  INT,
	group_id INT,
	PRIMARY KEY (user_id, group_id),
	FOREIGN KEY (user_id) REFERENCES user (id),
	FOREIGN KEY (group_id) REFERENCES group (id)
);

CREATE TABLE room (
	id   INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20)
);

CREATE TABLE group_control (
	group_id INT,
	room_id  INT,
	PRIMARY KEY (group_id, room_id),
	FOREIGN KEY (group_id) REFERENCES group (id),
	FOREIGN KEY (room_id) REFERENCES room (id)
);

CREATE TABLE station (
	id          INT PRIMARY KEY AUTO_INCREMENT,
	ip          VARCHAR(15) UNIQUE,
	name        VARCHAR(20),
	description VARCHAR(50),
	room_id     INT,
	FOREIGN KEY (room_id) REFERENCES room (id)
);

CREATE TABLE sensor (
	id          INT PRIMARY KEY AUTO_INCREMENT,
	name        VARCHAR(20),
	description VARCHAR(50),
	value       INT,
	pint        INT,
	station_id  INT,
	FOREIGN KEY (station_id) REFERENCES station (id)
);

CREATE TABLE relay (
	id          INT PRIMARY KEY AUTO_INCREMENT,
	name        VARCHAR(20),
	description VARCHAR(50),
	status      TINYINT(1),
	pin         INT,
	station_id  INT,
	FOREIGN KEY (station_id) REFERENCES station (id)
);