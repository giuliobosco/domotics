<?php
/*
 * Copyright 2018 Giulio Bosco (giuliobva@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
/**
 * project: <project-name> ([<project-git-url>])
 * description: <project-description>
 *
 * author: Giulio Bosco (giuliobva@gmail.com)
 * version: 1.0
 * date: 24.05.2018
 *
 * file: rooms.php
 */

/**
 * Get the controllable rooms from user id.
 * @param $db mysqli_driver Database where run the query.
 * @param $id int ID of the user for select rooms.
 * @return mysqli_result Result of the query, id and name of the room.
 */
function roomsByUserId($db, $id) {
	$rooms_query = "
SELECT
	room.id,
	room.name
FROM db_domotics.room
	JOIN users_group_control ugc
		ON room.id = ugc.room_id
	JOIN users_group ug
		ON ugc.users_group_id = ug.id
	JOIN user_appertain ua
		ON ug.id = ua.users_group_id
	JOIN user
		ON ua.user_id = user.id
WHERE user.id = '$id';
	";
	
	return mysqli_query($db, $rooms_query);
}

/**
 * Get Relays aveable in the room selected by id.
 * @param $db mysqli_driver Database where run the query.
 * @param $id int ID of the user for select rooms.
 * @return bool|mysqli_result Result of the query, id, name, status, icon of the relay.
 */
function relayByRoomId($db, $id) {
	$relay_query = "SELECT
	relay.id,
	relay.name,
	relay.status,
	relay.icon
FROM db_domotics.relay
	JOIN station
		ON relay.station_id = station.id
WHERE room_id = '$id';";
	
	return mysqli_query($db, $id);
}

?>