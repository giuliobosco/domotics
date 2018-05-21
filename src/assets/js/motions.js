/*
 * Copyright 2018 Giulio Bosco (giuliobva@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
/*
 * project: <project-name> ([<project-git-url>])
 * description: <project-description>
 *
 * author: Giulio Bosco (giuliobva@gmail.com)
 * version: 1.0
 * date: <date>
 *
 * file: app.js
 */

var usernameDisplay = 0;

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

var passwordDisplay = 0;
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