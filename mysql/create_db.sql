/**
  * Database: db_domotics
  * Use: This database is used for manage the domotic of a house.
  * Project: domotics (https://github.com/giuliobosco/domotics)
  * Author: Giulio Bosco
  * Version: 1.0
  * Date: 21.05.2018
	*/
CREATE DATABASE db_domotics;

/**
	* Users with access to the domotic system.
	* Attributes:
	* id ID of the user.
	* username Username of the user.
	* password Password of the user, citrate with the algorithm RSA256.
	* name Name of the user.
	* surname Surname of the user.
	* email E-Mail address of the user.
	*/
CREATE TABLE user (
	id       INT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(50) UNICODE NOT NULL,
	password VARCHAR(50)         NOT NULL,
	name     VARCHAR(30)         NOT NULL,
	surname  VARCHAR(30)         NOT NULL,
	email    VARCHAR(80)         NOT NULL
);

/**
	* Groups of users with access to the domotics system.
	* Used for have easiest control of the access.
	* Attributes:
	* id Id of the group.
	* name Name of the group.
	* description Description of the group.
 */
CREATE TABLE group (
	id          INT PRIMARY KEY AUTO_INCREMENT,
	name        VARCHAR(30) NOT NULL,
	description VARCHAR(30) NOT NULL
);

/**
	* User appertain to group.
	* One user can have many groups.
	* One group can have many users.
	* Attributes:
	* user_id User ID.
	* group_id Group ID.

 */
CREATE TABLE user_appertain (
	user_id  INT,
	group_id INT,
	PRIMARY KEY (user_id, group_id),
	FOREIGN KEY (user_id) REFERENCES user (id),
	FOREIGN KEY (group_id) REFERENCES group (id)
);

/**
	* Room of domotics house.
	* id ID of the room.
	* name Name of the room.
 */
CREATE TABLE room (
	id   INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20)
);

/**
	* Groups can control rooms.
	* Attributes:
	* group_id Group ID.
	* room_id Room ID.
 */
CREATE TABLE group_control (
	group_id INT,
	room_id  INT,
	PRIMARY KEY (group_id, room_id),
	FOREIGN KEY (group_id) REFERENCES group (id),
	FOREIGN KEY (room_id) REFERENCES room (id)
);

/**
	* Station is an arduino ethernet or wifi with the relay or the sensors.
	* id ID of the station
	* ip IP of the station in the network.
	*     Like: "192.168.1.1"
	* name Name of the station.
	* description Description of the station.
	* room_id ID of the room where is positioned the station.
 */
CREATE TABLE station (
	id          INT PRIMARY KEY AUTO_INCREMENT,
	ip          VARCHAR(15) UNIQUE,
	name        VARCHAR(20),
	description VARCHAR(50),
	room_id     INT,
	FOREIGN KEY (room_id) REFERENCES room (id)
);

/**
	* Sensor in the room attached to the station.
	* Light sensor, movement sensor, temperature sensor.
	* Attributes:
	* id ID of the sensor.
	* name Name of the sensor.
	* value Value of the sensor.
	* pin Pin of the station where is attached the sensor.
	* station_id Station where is attached the sensor.
 */
CREATE TABLE sensor (
	id          INT PRIMARY KEY AUTO_INCREMENT,
	name        VARCHAR(20),
	description VARCHAR(50),
	value       INT,
	pin         INT,
	station_id  INT,
	FOREIGN KEY (station_id) REFERENCES station (id)
);

/**
	* Relay in the room attached to the station.
	* Light, Electric Socket.
	* Attributes:
	* id ID of the relay.
	* name Name of the relay.
	* description Description of the relay.
	* status Status of the relay.
	* pin Pin of the station where is attached the sensor.
	* station_id Station where is attached the sensor.
 */
CREATE TABLE relay (
	id          INT PRIMARY KEY AUTO_INCREMENT,
	name        VARCHAR(20),
	description VARCHAR(50),
	status      TINYINT(1),
	pin         INT,
	station_id  INT,
	FOREIGN KEY (station_id) REFERENCES station (id)
);