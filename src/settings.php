<?php
include('session.php');
include('assets/php/rooms.php');
/*
 * Copyright 2018 Giulio Bosco (giuliobva@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
/*
 * project: Domotics (https://github.com/giuliobosco/domotics.git)
 * description: Home made domotics with control by web interface and arduino.
 *
 * author: Giulio Bosco (giuliobva@gmail.com)
 * version: 1.0
 * date: 23.05.2018
 *
 * file: settings.php
 */
?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">

	<meta property="og:type" content="website">
	<meta name="keywords" content="<keywords>">
	<meta property="og:title" content="<title>">
	<meta name="description" content="<description>">
	<meta property="og:description" content="<descriptio>">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>domotics &verbar; settings</title>

	<!-- stylesheet - CSS -->
	<link rel="stylesheet" type="text/css" href="lib/font/font-awesome.min.css">             <!-- special characters -->
	<link rel="stylesheet" type="text/css" href="lib/css/bootstrap.min.css">                          <!-- Bootstrap -->
	<link rel="stylesheet" type="text/css" href="assets/css/settings.css">

	<!-- Scripts - JavaScript -->
	<script src="lib/js/jquery.js"></script>                                                          <!--- jQuery ---->
	<script src="lib/js/utility.js"></script>                                                         <!--- Utility --->
	<script src="assets/js/settings-manager.js"></script>
</head>

<body>

<div class="col-md-3 navbar">
	<h1 class="col-md-offset-2">domotics</h1>
	<h4 class="col-md-offset-2">home controller</h4>
	<ul class=" col-md-offset-2 col-md-8">
		<li><a href="dashboard.php">dashboard<i class="fa fa-dashboard pull-right"></i></a></li>
		<li><a href="#">rooms<i class="fa fa-bed pull-right"></i></a></li>
		<li class="active"><a href="settings.php">settings<i class="fa fa-cog pull-right"></i></a></li>
	</ul>
	<div class="bottom col-md-offset-2 col-md-8">
		<h4 class="pull-left">
			<a href="logout.php"><i class="fa fa-sign-out"></i> logout</a>
		</h4>

		<h4 class="pull-right">
			<a href="https://github.com/giuliobosco/domotics"><i class="fa fa-info-circle"></i></a>
		</h4>
	</div>
</div>
<div class="col-md-9 container">
	<div class="container-header col-md-12">
		<h1><i class="fa fa-bars mobile"></i> settings</h1>
	</div>
	<div class="col-md-12 settings-section">
		<div class="section-title pull-left col-md-12 no-padding">
			<h2 class="pull-left">rooms</h2>
			<h2 class="pull-right text-right"><i class="fa fa-angle-down"></i> </h2>
		</div>
		<div class="section-content">
			<?php
			$user_id = $user['id'];
			// SELECT ROOMS WITH USERNAME ID
			$rooms_result = roomsByUserId($db, $user['id']);
			
			if (mysqli_num_rows($rooms_result)>0) {

				while ($room_row = mysqli_fetch_assoc($rooms_result)) {
					//TODO: add html
					
					$relay_result = relayByRoomId($db, $room_row['id']);
					
					if (mysqli_num_rows($light_result)>0) {
						while ($light_row = mysqli_fetch_assoc($light_result)) {

						}
					}
				}
			}
			?>
			<div class="content-elem col-md-12">
				<h3 class="pull-left">Room Name</h3>
				<h3 class="pull-right"> <i class="fa fa-ellipsis-v"></i></h3>
				<h3 class="pull-right"><i class="fa fa-lightbulb-o"></i></h3>

				<div class="col-md-12 cont-elem-intern">
					<h4 class="pull-left"><i class="fa fa-lightbulb-o"></i> </h4>
					<h4 class="pull-left"> Light Name</h4>

					<label class="switch pull-right">
						<input type="checkbox" name="r1" onclick="relayOn(this)">
						<span class="slider round"></span>
					</label>

					<h4 class="pull-right"><i class="fa fa-edit"></i></h4>

					<div class="pull-right edit">
						name: <input type="text">
						description: <input type="text">
						station: <input type="text"> <!-- TODO: create select for stations in room -->
						pin: <input> <!-- TODO: create select for aveables pin in station -->
					</div>

				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
