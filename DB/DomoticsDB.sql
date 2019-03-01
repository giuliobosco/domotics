CREATE TABLE `beamer`
(
	`pin` varchar(255) PRIMARY KEY,
	`arduino` varchar(255) PRIMARY KEY
);

CREATE TABLE `light`
(
	`pin` varchar(255) PRIMARY KEY,
	`arduino` varchar(255) PRIMARY KEY
);

CREATE TABLE `curtain`
(
	`pin` varchar(255) PRIMARY KEY,
	`arduino` varchar(255) PRIMARY KEY
);

CREATE TABLE `sensor`
(
	`pin` varchar(255) PRIMARY KEY,
	`arduino` varchar(255) PRIMARY KEY
);

CREATE TABLE `arduino`
(
	`id` varchar(255) PRIMARY KEY,
	`ip` varchar(255),
	`key` varchar(255),
	`room` varchar(255)
);

CREATE TABLE `room`
(
	`name` varchar(255) PRIMARY KEY
);

ALTER TABLE `room` ADD FOREIGN KEY (`name`) REFERENCES `arduino` (`room`);

ALTER TABLE `arduino` ADD FOREIGN KEY (`ip`) REFERENCES `beamer` (`arduino`);

ALTER TABLE `arduino` ADD FOREIGN KEY (`ip`) REFERENCES `light` (`arduino`);

ALTER TABLE `arduino` ADD FOREIGN KEY (`ip`) REFERENCES `curtain` (`arduino`);

ALTER TABLE `arduino` ADD FOREIGN KEY (`ip`) REFERENCES `sensor` (`arduino`);
