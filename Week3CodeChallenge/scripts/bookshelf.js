window.onload = function () {
    var commentForm = document.getElementById("submitButton");
    commentForm.addEventListener("click", addNewComment);
};

function addNewComment(event) {
    event.preventDefault();
    var newComment = {
        "email": document.getElementById('emailInput').value,
        "comment": document.getElementById('commentInput').value
    }
    document.getElementById("suggestedCities").innerHTML = newComment.email + ":     " + newComment.comment;

    console.log(newComment);
}