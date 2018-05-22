<?php
include('config.php');
session_start();

$user_check = $_SESSION['login_user'];

$ses_sql = mysqli_query(
	$db,
	"SELECT user.id, user.username, user.name,user.surname FROM db_domotics.user WHERE user.username = '$user_check'"
);

$user = mysqli_fetch_array($ses_sql, MYSQLI_ASSOC);

if (!isset($_SESSION['login_user'])) {
	header("location:login.php");
}
?>