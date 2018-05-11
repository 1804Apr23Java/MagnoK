1. USA
Define function getUSA()

Find the html element that contains "USA".

Print that element's contents.

function getUSA() {
    var text = "USA";
    var tags = document.body.getElementsByTagName("*");
    for(var a = 0; a < tags.length; i++) {
        var n = tags.textContent.search(text);
        if(n != 1) {
            alert(tags[i].textContent);
            break;
        }
        return n;
    }
};

window.onload = function() {
	getUSA();
}