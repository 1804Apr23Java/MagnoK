/**
 * 
 */

function fileNameChange() {
	var x = document.getElementById("myFile");
	var txt = "";
	if ('files' in x) {
		txt += "<br><strong>" + (i + 1) + ". file</strong><br>";
		var file = x.files[i];
	} else {
		if (x.value == "") {
			txt += "Please Select a File";
		} else {
			txt += "The files property is not supported by your browser!";
			// If the browser does not support the files property, it will
			// return the path of the selected file instead.
			txt += "<br>The path of the selected file: " + x.value;
		}
	}
	document.getElementById("fileName").innerHTML = txt;
}