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
 * file: session.php
 * file description: check the authenticity of the session with a right login.
 */

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