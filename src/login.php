<?php
include("config.php");
session_start();
if ($_SERVER["REQUEST_METHOD"] == "POST") {
	// username and password sent from form
	/*
		$myusername = mysqli_real_escape_string($db, $_POST['username']);
		$mypassword = mysqli_real_escape_string($db, $_POST['password']);

		$sql = "SELECT id FROM admin WHERE username = '$myusername' and passcode = '$mypassword'";
		$result = mysqli_query($db, $sql);
		$row = mysqli_fetch_array($result, MYSQLI_ASSOC);
		$active = $row['active'];

		$count = mysqli_num_rows($result);

		// If result matched $myusername and $mypassword, table row must be 1 row

		if ($count == 1) {
			session_register("myusername");
			$_SESSION['login_user'] = $myusername;

			header("location: welcome.php");
		} else {
			$error = "Your Login Name or Password is invalid";
		}*/
	if ($_SESSION['username'] == null) {
		$username = mysqli_real_escape_string($db, $_POST['username']);
		$sql = "SELECT id FROM user WHERE username = '$username'";
		$result = mysqli_query($db, $sql);
		$row = mysqli_fetch_array($result, MYSQLI_ASSOC);

		$count = mysqli_num_rows($result);
		if ($count == 1) {
			session_register("username");
			$_SESSION['username'] = $username;

			header("login.php");
		} else {
			$error = "Username not valid! Retry!";
		}
	} else {
		$username = $_SESSION['username'];
		$password = mysqli_real_escape_string($db, $_POST['password']);
		$sql = "SELECT id FROM user WHERE id = '$username' and password = '$password'";
		$result = mysqli_query($db, $sql);
		$row = mysqli_fetch_array($result, MYSQLI_ASSOC);

		$count = mysqli_num_rows($result);
		if ($count == 1) {

		}
	}


}

$passDisplay = "none";
$userDisplay = "block";
?>
<html>

<head>
	<title>Domotics</title>

	<meta charset="UTF-8">
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

			<form action="" method="post">
				<div class="box-container">
					<p id="white-username"><small>username</small></p>
					<p id="username-p"><small>username:<small></small></p>
					<input type="text" name="username" placeholder="username" class="box" oninput="toggleUsername(this)" onselect="$(this)[0].css('margin-bottom','1px solid #0090c2')">

					<p id="white-password"><small>password</small></p>
					<p id="password-p"><small>password:</small></p>
					<input type="password" name="password" placeholder="password" class="box" oninput="togglePassword(this)">

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