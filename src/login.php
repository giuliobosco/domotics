<?php
include("config.php");
session_start();
if ($_SERVER["REQUEST_METHOD"] == "POST") {
	// username and password sent from form
	$myusername = mysqli_real_escape_string($db, $_POST['username']);
	$mypassword = mysqli_real_escape_string($db, $_POST['password']);

	//$sql = "SELECT id FROM db_domotics.user WHERE user.username = '$myusername' and user.password = '$mypassword'";
	$sql = "SELECT id FROM db_domotics.user";
	$result = mysqli_query($db, $sql);
	$row = mysqli_fetch_array($result, MYSQLI_ASSOC);
	$active = $row['active'];

	$count = mysqli_num_rows($result);
	// If result matched $myusername and $mypassword, table row must be 1 row

	if ($count == 1) {
		//session_register("myusername");
		$_SESSION['login_user'] = $myusername;

		header("location: welcome.php");
	} else {
		$error = "Your Login Name or Password is invalid";
	}


}

?>
<html>

<head>
	<title>Domotics</title>

	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="lib/font/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="lib/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">

	<script src="lib/js/jquery.js"></script>
	<script>console.log('<?php echo $debugger; ?>' + "s");</script>
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