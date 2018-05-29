<?php
/*
 * Copyright 2018 Giulio Bosco (giuliobva@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
/*
 * project: <project-name> (https://github.com/giuliobosco/domotics.git)
 * description: Home made domotics with control by web interface and arduino.
 *
 * author: Giulio Bosco (giuliobva@gmail.com)
 * version: 1.0
 * date: <date>
 *
 * file: index.php
 */
function dmt_getRelayData($db, $id) {
	$relay_data_sql = "SELECT r.pin, s.ip FROM db_domotics.relay r JOIN db_domotics.station s ON r.station_id = s.id WHERE r.id = $id;";
	
	$relay_data_result = mysqli_query($db, $relay_data_sql);
	$relay_data_row = mysqli_fetch_array($relay_data_result, MYSQLI_ASSOC);
	
	return $relay_data_row;
}

function dmt_changeRelayStatus($db, $id, $status) {
	$relay_data = dmt_getRelayData($db, $id);
	
	$relay_ip = $relay_data['ip'];
	$relay_pin = $relay_data['pin'];
	
	$relay_status_sql = "UPDATE db_domotics.relay SET relay.status = $status WHERE relay.id = $id";
	mysqli_query($db,$relay_status_sql);
	return "http://" . $relay_ip . "/?pin" . $relay_pin . "=" . $status;
}

?>