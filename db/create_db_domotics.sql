/*
 * The MIT License
 *
 * Copyright 2018 giuliobosco.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/**
 * SQL Create database script.
 *
 * @author paologuebeli (paolo.guebeli@samtrevano.ch)
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0.3 (2019-02-22 - 2019-05-03)
 */

/* create the database */
CREATE DATABASE domotics;

/* create the room table */
CREATE TABLE domotics.room (
	name VARCHAR(255) PRIMARY KEY
);

/* create the arduino table */
CREATE TABLE domotics.arduino (
	client_id     VARCHAR(255) PRIMARY KEY,
	ip            VARCHAR(255),
	root_password VARCHAR(255),
	client_key    VARCHAR(255),
	room          VARCHAR(255),

	FOREIGN KEY (room)
		REFERENCES domotics.room (name)
);

/* create the light table */
CREATE TABLE domotics.light (
	pin     VARCHAR(255),
	arduino VARCHAR(255),
	name    VARCHAR(255),
	status  INT(1),
	PRIMARY KEY (pin, arduino),
	FOREIGN KEY (arduino)
		REFERENCES domotics.arduino (client_id)
);


/* create the beamer table */
CREATE TABLE domotics.beamer (
	pin     VARCHAR(255),
	arduino VARCHAR(255),
	name    VARCHAR(255),
	PRIMARY KEY (pin, arduino),
	FOREIGN KEY (arduino)
		REFERENCES domotics.arduino (client_id)
);

/* create the curtain table */
CREATE TABLE domotics.curtain (
	pin     VARCHAR(255),
	arduino VARCHAR(255),
	name    VARCHAR(255),
	PRIMARY KEY (pin, arduino),
	FOREIGN KEY (arduino)
		REFERENCES domotics.arduino (client_id)
);

/* create the sensor type table */
CREATE TABLE domotics.sensorType (
	name VARCHAR(255) PRIMARY KEY
);

/* create the sensor table */
CREATE TABLE domotics.sensor (
	pin     VARCHAR(255),
	arduino VARCHAR(255),
	type    VARCHAR(255),
	name    VARCHAR(255),
	PRIMARY KEY (pin, arduino),
	FOREIGN KEY (arduino)
		REFERENCES domotics.arduino (client_id),
	FOREIGN KEY (type)
		REFERENCES domotics.sensorType (name)
);

/* create the lightButton table */
CREATE TABLE domotics.lightButton (
	pin      VARCHAR(255),
	lightPin VARCHAR(255),
	arduino  VARCHAR(255),
	name    VARCHAR(255),
	PRIMARY KEY (pin, arduino),
	FOREIGN KEY (arduino)
		REFERENCES domotics.arduino (client_id),
	FOREIGN KEY (lightPin)
		REFERENCES domotics.light (pin)
);
