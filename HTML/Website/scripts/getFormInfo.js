
function displayFormInfo() {
    document.getElementById("exampleFormControlTextarea1");
    text = exampleFormControlTextarea1.value;
    console.log(text.value);
    document.getElementById("suggestedCities").innerHTML = "Comment submitted";
};

function getComment() {
    document.getElementById("exampleFormControlTextarea1").value;
};

function getPokemon(xhr) {
	var res = JSON.parse(xhr.responseText);
	var html = "<h4>chosen pokemon has name: " + res.name + "</h4>";
	document.getElementById("pokeResult").innerHTML = html;
};

window.onload = function () {
    displayFormInfo();
    document.getElementById("submit").addEventListener(
        "click",
        function () {
            document.getElementById("exampleFormControlTextarea1").value;
        });    
} 