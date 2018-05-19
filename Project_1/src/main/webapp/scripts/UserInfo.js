/**
 *  Will contain functions used to obtain user information
 */

function sendAjaxGet(url, func) {

	var xhr = new XMLHttpRequest()
			|| new ActiveXObject("Microsoft.HTTPRequest");

	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			func(this);
		}
	};

	xhr.open("GET", url, true);
	xhr.send();
}

function getUserInfo(xhr) {
	if (xhr.responseText) {
		console.log(xhr.responseText);
		var res = JSON.parse(xhr.responseText);
		console.log(res);
		// If there is noone logged in it will kick them back to the login screen
		if (res.username) {
			document.getElementById("user").innerHTML = "you are logged in as "
					+ res.username;
		} else {
			window.location = "http://localhost:8087/Project_1/login";
		}
	} else {
		window.location = "http://localhost:8087/Project_1/login";
	}
}

window.onload = function() {
	console.log("executed window.onload");
	sendAjaxGet("http://localhost:8087/Project_1/user", getUserInfo);
}