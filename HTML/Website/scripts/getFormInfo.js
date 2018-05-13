
function displayFormInfo() {
    document.getElementById("exampleFormControlTextarea1");
    text = exampleFormControlTextarea1.value;
    console.log(text.value);
    
};

window.onload = function () {
    //displayFormInfo();
    var commentForm = document.getElementById("submitButton");
    commentForm.addEventListener("click",addNewComment);
    // document.getElementById("submit").addEventListener(
    //     "click",
    //     function () {
    //         document.getElementById("exampleFormControlTextarea1").value;
    //     });    
};

function addNewComment(event) {
    alert ("Hello!");
    event.preventDefault();
    var newComment = {
        "email": document.getElementById('emailInput').value,
        "comment": document.getElementById('commentInput').value
    }
    document.getElementById("suggestedCities").innerHTML = newComment.email + ":     " + newComment.comment;

    console.log(newComment);
    // var xhr = new XMLHttpRequest();
    // xhr.open("POST",serverURL+"comment", true);
    // xhr.setRequestHeader("Content-Type","application/json;charset=UTF-8");
    // xhr.onreadystatechange = function () {
    //     if (xhr.readyState != 4 || xhr.status != 200) return;

    //     console.log("Success: " + xhr.responseText);
    //     commentForm.reset();
    // };
    // document.getElementById("suggestedCities").innerHTML = "Comment submitted";
    // xhr.send(JSON.parse(newComment).innerHTML);
}

// function getPokemon(xhr) {
// 	var res = JSON.parse(xhr.responseText);
// 	var html = "<h4>chosen pokemon has name: " + res.name + "</h4>";
// 	document.getElementById("pokeResult").innerHTML = html;
// };
