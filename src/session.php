<?php
include('config.php');
session_start();

$user_check = $_SESSION['login_user'];

$ses_sql = mysqli_query($db,"SELECT user.id, user.name FROM db_domotics.user WHERE user.username = '$user_check' ");

$row = mysqli_fetch_array($ses_sql,MYSQLI_ASSOC);

$login_session = $row['name'];

if(!isset($_SESSION['login_user'])){
	header("location:login.php");
}
?>