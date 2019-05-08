function login() {
	var username = $("#username").val();
	var password = $("#password").val();

	if (username.length > 0 && password.length > 0) {
		var json = $.getJSON("data_/login", {username: username, password: password})
			.done(function(data) {
				if (data.status === "ok" && data.username === username) {
					window.location = "#!/rooms";
				} else {
					$('#errorMessage').text(data.message);
				}
			})
			.fail(function () {
				$('#errorMessage').text("Server Error");
			});
	} else {
		$('#errorMessage').text('Insert username and password');
	}
}

$('#loginBtn').click(login);