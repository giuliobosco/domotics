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
 * project: <project-name> (https://github.com/giuliobosco/domotics.git)
 * description: Home made domotics with control by web interface and arduino.
 *
 * author: Giulio Bosco (giuliobva@gmail.com)
 * version: 1.0
 * date: 29.05.2018
 *
 * file: login.php
 */
include("config.php");
session_start();
if ($_SERVER["REQUEST_METHOD"] == "POST") {
	// username and password sent from form
	$myusername = mysqli_real_escape_string($db, $_POST['username']);
	$mypassword = mysqli_real_escape_string($db, $_POST['password']);

	//$sql = "SELECT id FROM db_domotics.user WHERE user.username = '$myusername' and user.password = '$mypassword'";
	$sql = "SELECT id FROM db_domotics.user WHERE username = '$myusername' AND password = '$mypassword'";
	$result = mysqli_query($db, $sql);
	$row = mysqli_fetch_array($result, MYSQLI_ASSOC);
	$active = $row['active'];

	$count = mysqli_num_rows($result);
	// If result matched $myusername and $mypassword, table row must be 1 row

	if ($count == 1) {
		//session_register("myusername");
		$_SESSION['login_user'] = $myusername;

		header("location: dashboard.php");
	} else {
		$error = "Your Login Name or Password is invalid";
	}


}

?>
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
	  -- project: <project-name> (https://github.com/giuliobosco/domotics.git)
      -- description: Home made domotics with control by web interface and arduino.
	  --
	  -- author: Giulio Bosco (giuliobva@gmail.com)
	  -- version: 1.0
	  -- date: 05.06.2018
	  --
	  -- file: login.php
	  -->
	
	<meta property="og:type" content="website">
	<meta name="keywords" content="<keywords>">
	<meta property="og:title" content="<title>">
	<meta name="description" content="<description>">
	<meta property="og:description" content="<descriptio>">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>Domotics</title>

	<link rel="stylesheet" type="text/css" href="lib/font/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="lib/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">

	<script src="lib/js/jquery.js"></script>
</head>

<body>
<script src="assets/js/motions.js"></script>

<div>
	<div class="col-md-3 form-login">
		<div>
			<h3>domotics</h3>
			<p>
				<small>home controller</small>
			</p>
		</div>

		<div class="login-container">
			<!-- TODO: edit action with echo htmlspecialchars form validation-->
			<form action="login.php?_ijt=k3vu23bmmc0gqsmlhulscj9rf7" method="post">
				<div class="box-container">
					<p id="white-username">
						<small>username</small>
					</p>
					<p id="username-p">
						<small>username:
							<small></small>
					</p>
					<input type="text" name="username" placeholder="username" class="box"
					       oninput="toggleUsername(this)">
					<!--onselect="$(this)[0].css('margin-bottom','1px solid #0090c2')">-->

					<p id="white-password">
						<small>password</small>
					</p>
					<p id="password-p">
						<small>password:</small>
					</p>
					<input type="password" name="password" placeholder="password" class="box"
					       oninput="togglePassword(this)">

					<input type="submit" value="login" class="col-xs-8 col-xs-offset-4 login-btn"><br>
				</div>
			</form>

			<div style="font-size:11px; color:#cc0000; margin-top:10px"><?php echo $error; ?></div>

			<div class="copyright">
				<span class="text-left">
					&copy; 2018 domotics
				</span>

				<span class="pull-right text-right">
					red.home
				</span>
			</div>
		</div>

	</div>

</div>

</body>
</html>