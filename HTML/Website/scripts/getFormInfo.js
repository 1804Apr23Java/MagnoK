
function displayFormInfo() {
    document.getElementById("suggestedCities").innerHTML = "Comment submitted";
};

function getComment() {
    document.getElementById("exampleFormControlTextarea1").value;
};

console.log(document.getElementById("exampleFormControlTextarea1").value);

window.onload = function () {
    displayFormInfo();
    document.getElementById("submit").addEventListener(
        "click",
        function () {
            document.getElementById("exampleFormControlTextarea1").value;
        });    
} 