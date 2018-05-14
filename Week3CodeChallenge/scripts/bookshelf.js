window.onload = function () {
    var commentForm = document.getElementById("submitButton");
    commentForm.addEventListener("click", addNewBook);
};

function addNewBook(event) {
    event.preventDefault();
    var newBook = {
        "email": document.getElementById('emailInput').value,
        "book": document.getElementById('bookInput').value
    }
    document.getElementById("newBooks").innerHTML = newBook.email + ":     " + newBook.book;

    console.log(newComment);
}