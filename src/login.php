<?php
include("config.php");
session_start();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
	// username and password sent from form

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
	}
}
?>
<html>

<head>
	<title>Domotics</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">

</head>

<body bgcolor="#FFFFFF">

<div align="center">
	<div class="col-md-3 form-login">
		<div style="background-color:#333333; color:#FFFFFF; padding:3px;"><b>Domotics</b></div>

		<div style="margin:30px">

			<form action="" method="post">
				<label>UserName :</label><input type="text" name="username" class="box"/><br/><br/>
				<label>Password :</label><input type="password" name="password" class="box"/><br/><br/>
				<input type="submit" value=" Submit "/><br/>
			</form>

			<div style="font-size:11px; color:#cc0000; margin-top:10px"><?php echo $error; ?></div>

		</div>

	</div>

</div>

</body>
</html>