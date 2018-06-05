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
 * file: motion.js
 */

/**
 * Username CSS Property Display status.
 * @type {number}
 */
var usernameDisplay = 0;

/**
 * Username toggle CSS Property Display status.
 * @param obj Username Input.
 */
function toggleUsername(obj) {
	var value = obj.value;
	if (value.length == 0 && usernameDisplay == 1) {
		$('#username-p').slideUp("fast");
		$('#white-username').slideDown("fast");
	} else if (value.length == 1 && usernameDisplay == 0) {
		$('#username-p').slideDown("fast");
		$('#white-username').slideUp("fast");
	}
	usernameDisplay = value.length;
}

/**
 * Password CSS Property Display status.
 * @type {number}
 */
var passwordDisplay = 0;

/**
 * Password toggle CSS Property Display status.
 * @param obj
 */
function togglePassword(obj) {
	var value = obj.value;
	if (value.length == 0 && passwordDisplay == 1) {
		$('#password-p').slideUp("fast");
		$('#white-password').slideDown("fast");
	} else if (value.length == 1 && passwordDisplay == 0) {
		$('#password-p').slideDown("fast");
		$('#white-password').slideUp("fast");
	}
	passwordDisplay = value.length;
}