window.onload = function () {
    var commentForm = document.getElementById("submitButton");
    commentForm.addEventListener("click", addNewBook);
    commentForm.addEventListener("click", addNewBookList);
};

function addNewBookList(event) {
    // Create <li> node
    var node = document.createElement("LI");
    var newBook = {
        "email": document.getElementById('emailInput').value,
        "book": document.getElementById('bookInput').value
    }
    var info = document.createTextNode(newBook.book);
    // Add book to 
    node.appendChild(info);
    document.getElementById("newBooks").appendChild(node);
}

function addNewBook(event) {
    event.preventDefault();
    var newBook = {
        "email": document.getElementById('emailInput').value,
        "book": document.getElementById('bookInput').value
    }
    document.getElementById("newBooks").innerHTML = newBook.email + ":     " + newBook.book;

    console.log(newComment);
}