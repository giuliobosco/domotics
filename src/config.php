<?php
define('DB_SERVER', 'localhost');
define('DB_USERNAME', 'root');
define('DB_PASSWORD', 'root');
define('DB_DATABASE', 'db_domotics');
$db = mysqli_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_DATABASE);

if (!$db) {
	echo "Error: Unable to connect to MySQL." . PHP_EOL;
	echo "<br>Debugging errno: " . mysqli_connect_errno() . PHP_EOL;
	echo "<br>Debugging error: " . mysqli_connect_error() . PHP_EOL;
	exit;
}

?>