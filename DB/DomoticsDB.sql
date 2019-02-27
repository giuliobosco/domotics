CREATE TABLE `beamer` 
(
	`pin` varchar(255),
	`arduino` varchar(255)
);

CREATE TABLE `light` 
(
	`pin` varchar(255),
	`arduino` varchar(255)
);

CREATE TABLE `curtain` 
(
	`pin` varchar(255),
	`arduino` varchar(255)
);

CREATE TABLE `sensor` 
(
	`pin` varchar(255),
	`arduino` varchar(255)
);

CREATE TABLE `arduino` 
(
	`ip` varchar(255),
	`key` varchar(255),
	`room` varchar(255)
);

CREATE TABLE `room` 
(
	`name` varchar(255)
);

ALTER TABLE `room` ADD FOREIGN KEY (`name`) REFERENCES `arduino` (`room`);

ALTER TABLE `arduino` ADD FOREIGN KEY (`ip`) REFERENCES `beamer` (`arduino`);

ALTER TABLE `arduino` ADD FOREIGN KEY (`ip`) REFERENCES `light` (`arduino`);

ALTER TABLE `arduino` ADD FOREIGN KEY (`ip`) REFERENCES `curtain` (`arduino`);

ALTER TABLE `arduino` ADD FOREIGN KEY (`ip`) REFERENCES `sensor` (`arduino`);
