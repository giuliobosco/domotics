<?php
/*
 * Copyright 2018 Giulio Bosco (giuliobva@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
/*
 * project: Domotics (https://github.com/giuliobosco/domotics.git)
 * description: Home made domotics with control by web interface and arduino.
 *
 * author: Giulio Bosco (giuliobva@gmail.com)
 * version: 1.0
 * date: 05.06.2018
 *
 * file: dashboard.php
 */
include('session.php');
include('assets/php/rooms.php');
include('assets/php/relay_controller.php');

if ($_SERVER['REQUEST_METHOD'] == "GET") {
	if (sizeof($_GET) == 1 && !empty($_GET['r'])) {
		$relay = $_GET['r'];
		$relay_name = explode("-", $relay);
		$relay_status_sql = "UPDATE db_domotics.relay SET relay.status = '$relay_name[1]' WHERE relay.id = '$relay_name[0]';";
		mysqli_query($db, $relay_status_sql);
		
		$topen = dmt_changeRelayStatus($db,$relay_name[0],$relay_name[1]);
	}
}

?>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	
	<!--
	  -- Copyright 2018 Giulio Bosco (giuliobva@gmail.com)
	  --
	  -- Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
	  -- documentation files (the "Software"), to deal in the Software without restriction, including without limitation
	  -- the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
	  -- and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
	  -- The above copyright notice and this permission notice shall be included in all copies or substantial portions
	  -- of the Software.
	  --
	  -- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
	  -- TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
	  -- THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
	  -- CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
	  -- DEALINGS IN THE SOFTWARE.
	  -->
	
	<!--
	  -- project: Domotics (https://github.com/giuliobosco/domotics.git)
	  -- description: Home made domotics with control by web interface and arduino.
	  --
	  -- author: Giulio Bosco (giuliobva@gmail.com)
	  -- version: 1.0
	  -- date:  05.06.2018
	  --
	  -- file: dashboard.php
	  -->
	
	<meta property="og:type" content="website">
	<meta name="keywords" content="<keywords>">
	<meta property="og:title" content="<title>">
	<meta name="description" content="<description>">
	<meta property="og:description" content="<descriptio>">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- pages title -->
	<title>title</title>
	
	<!-- stylesheet - CSS -->
	<link rel="stylesheet" type="text/css" href="lib/font/font-awesome.min.css">             <!-- special characters -->
	<link rel="stylesheet" type="text/css" href="lib/css/bootstrap.min.css">                          <!-- Bootstrap -->
	<link rel="stylesheet" type="text/css" href="assets/css/dashboard.css">
	
	<!-- Scripts - JavaScript -->
	<script src="lib/js/jquery.js"></script>                                                          <!--- jQuery ---->
	<script src="lib/js/utility.js"></script>                                                         <!--- Utility --->
	<script src="assets/js/lights-manager.js"></script>
	
	<script>
		$.ajax({
			type: 'GET',
			url: '<?php echo $topen ?>',
			dataType: 'xml',
			success: function (data) {
			}
		});
	</script>
</head>

<body>
<div class="col-md-3 navbar">
	<h1 class="col-md-offset-2">domotics</h1>
	<h4 class="col-md-offset-2">home controller</h4>
	<ul class=" col-md-offset-2 col-md-8">
		<li class="active"><a href="dashboard.php">dashboard<i class="fa fa-dashboard pull-right"></i></a></li>
		<li><a href="#">rooms<i class="fa fa-bed pull-right"></i></a></li>
		<li><a href="settings.php">settings<i class="fa fa-cog pull-right"></i></a></li>
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
		<h1><i class="fa fa-bars mobile"></i><?php echo $user['name']; ?>&apos;s dashboard</h1>
	</div>
	<div class="rooms col-md-12">
		<?php
		$user_id = $user['id'];
		
		$rooms_result = roomsByUserId($db,$user['id']);
		
		if (mysqli_num_rows($rooms_result) > 0) {
			while ($room_row = mysqli_fetch_assoc($rooms_result)) {
				
				
				$temperature = "21°C";
				
				$temperature_result = temperatureSensorByRoomId($db, $room_row['id']);
				
				$temperature_row = mysqli_fetch_array($temperature_result, MYSQLI_ASSOC);
				
				if (mysqli_num_rows($temperature_row) == 1) {
					$temperature = $temperature_row['value'] . "°C";
				}
				
				echo '<div class="room col-md-3"><div class="room-container">';
				echo '<div class="temperature pull-right"><h3>' . $temperature . '</h3></div>';
				echo '<div class="name pull-left"><h3><i class="fa fa-ellipsis-v"></i> ' . $room_row['name'] . '</h3></div>'; // TODO: add ellips-v action
				
				$relay_result = relayByRoomId($db, $room_row['id']);
				
				if (mysqli_num_rows($relay_result) > 0) {
					while ($relay_row = mysqli_fetch_assoc($relay_result)) {
						echo '<div class="relay">';
						echo '<h4 class="pull-left col-md-8"><i class="fa fa-' . $relay_row['icon'] . '"></i> ' . $relay_row['name'] . '</h4>';
						echo '<label class="switch pull-right">';
						if ($relay_row['status'] == 0) {
							echo '<input type="checkbox" name="r' . $relay_row['id'] . '" onclick="relayOn(this)">';
						} else {
							echo '<input type="checkbox" name="r' . $relay_row['id'] . '" onclick="relayOff(this)" checked>';
						}
						echo '<span class="slider round"></span>';
						echo '</label>';
						echo '</div>';
					}
				}
				
				echo '</div></div>';
			}
		}
		?>
	</div>
</div>
</body>
</html>