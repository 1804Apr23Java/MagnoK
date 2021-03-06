/**
 * 
 */
function sendAjaxGet(url, func) {

	var xhr = new XMLHttpRequest()
		|| new ActiveXObject("Microsoft.HTTPRequest");

	xhr.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			func(this);
		}
	};

	xhr.open("GET", url, true);
	xhr.send();
}

function getReimbInfo(xhr) {
	if (xhr.responseText) {
		console.log(xhr.responseText);
		var res = JSON.parse(xhr.responseText);
		console.log(res);
		// If there is noone logged in it will kick them back to the login screen
		if (res.id) {
			document.getElementById("rId").innerHTML = res.id;
			document.getElementById("reqNotes").innerHTML = res.reqNotes;
			document.getElementById("amount").innerHTML = res.amount;
			document.getElementById("reqDate").innerHTML = res.reqDate;
			// document.getElementById("reqImg").innerHTML = res.img;
			if(res.img === 'No file has been uploaded') {
				document.getElementById("reqImg").innerHTML = res.img;
			} else {
				document.getElementsByTagName("IMG")[0].setAttribute("src", "Project_1/images/"+res.img);
			}
		} else {
			window.location = "http://localhost:8087/Project_1/login";
		}
	} else {
		window.location = "http://localhost:8087/Project_1/login";
	}
}

function getPicture(xhr) {
	if (xhr.responseText) {
		console.log(xhr.responseText);
		var res = JSON.parse(xhr.responseText);
		console.log(res);
		// If there is noone logged in it will kick them back to the login screen
		document.getElementsByTagName("H1")[0].setAttribute("class", "democlass");
	}
}
http://localhost:8087/Project_1/pages/Login.html

window.onload = function () {
	console.log("executed window.onload");
	sendAjaxGet("http://localhost:8087/Project_1/reimbInfo", getReimbInfo);
}