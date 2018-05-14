// All functions in HW
window.onload = function () {
    //getUSA();
    //getPeopleInSales();
    //getAnchorChildren();
    //getHobbies();
    //getCustomAttribute();
    //getSum();
    //alertUserSkill();
    //favoriteColor();
    //employeeHover();
    //currentTimeFunc();
    //delayHelloWorld();
    //walkTheDOM(this.document.getElementsByTagName("html")[0], func);
    //Will display nodes traversed
    //console.log(n);
}

// 1. USA
// Define function getUSA()

// Find the html element that contains "USA".

// Print that element's contents.

function getUSA() {
    var cAttr = document.querySelectorAll("[data-customAttr]");
    for (var i = 0; i < cAttr.length; i++) {
        if (cAttr[i].dataset.customattr === "USA") {
            console.log(cAttr[i].textContent);
        }
    }
};

// 2. Sales

// Define function getPeopleInSales()

// Print the names of all the people in the sales department.

function getPeopleInSales() {
    var salesDept = document.getElementsByTagName("table")[0];
    for (var i = 0; i < salesDept.rows.length; i++) {
        var people = salesDept.rows[i].cells;
        if (people[1].textContent === "Sales") {
            console.log(people[0].textContent);
        }
    }
};

// 3. Click Here

// Define function getAnchorChildren()

// Find all anchor elements with a <span> child.

// Print the contents of <span>

function getAnchorChildren() {
    var anchorChildren = document.querySelectorAll("A>span");
    for (var i = 0; i < anchorChildren.length; i++) {
        console.log(anchorChildren[i].textContent);
    }
};

// 4. Hobbies
// Define function getHobbies()	
// Find all checked options in the 'skills' select element.

// Print the value and the contents.

function getHobbies() {
    var h = document.getElementsByName("hobbies")[0];

    // Will refresh value and will print currently selected option every 3 sec.
    setInterval(function () {
        var contenth = h.options[h.selectedIndex].textContent;
        var selh = h.options[h.selectedIndex].value;

        console.log("Hobby selected: " + contenth + " " + selh);
    }, 3000);

    var s = document.getElementsByName("skills")[0];
    setInterval(function () {
        var conts = s.options[s.selectedIndex].textContent;
        var sels = s.options[s.selectedIndex].value;

        console.log("Skill selected: " + conts + " " + sels);
    }, 3000);

};

// 5. Custom Attribute

// Define function getCustomAttribute()

// Find all elements with "data-customAttr" attribute

// Print the value of the attribute.

// Print the element that has the attribute.

function getCustomAttribute() {
    var cAttr = document.querySelectorAll("[data-customAttr]");
    for (var i = 0; i < cAttr.length; i++) {
        console.log(cAttr[i].dataset.customattr);
    }
};

// 6. Sum Event

// NOTE: Write unobtrusive Javascript

// Regarding these elements:

// <input id="num1" class="nums" type="text"/>

// <input id="num2" class="nums" type="text"/>

// <h3>Sum: span id="sum"></span></h3>


// Define onchange event handler.

// Add <input> element values.

// Put the sum in the <span> element.

// If values cannot be added, put "Cannot add" in the <span> element

function getSum() {
    // Add event listeners
    document.getElementById("num1").addEventListener("change", getSum);
    document.getElementById("num2").addEventListener("change", getSum);

    var num1 = parseInt(document.getElementById("num1").value);
    var num2 = parseInt(document.getElementById("num2").value);

    res = num1 + num2;
    console.log(res);

    if (res === 0) {
        document.getElementById("sum").innerHTML = "0";
    } else if (isNaN(res)) {
        document.getElementById("sum").innerHTML = "Cannot add!";
    } else {
        document.getElementById("sum").innerHTML = res;
    }
};

// 7. Skills Event

// NOTE: Write unobtrusive Javascript

// When user selects a skill, create an alert with a message similar to:

// "Are you sure CSS is one of your skills?"

// NOTE: no alert should appear when user deselects a skill.

function alertUserSkill() {
    // Add event listener
    var s = document.getElementsByName("skills")[0];
    document.querySelector("select[name=skills]").addEventListener("change", function () {
        alert("Are you sure " + s.options[s.selectedIndex].textContent + " is one of your skills?!");
    });
};

// 8. Favorite Color Event

// NOTE: Write unobtrusive Javascript

// NOTE: This is regarding the favoriteColor radio buttons.

// When a user selects a color, create an alert with a message similar to:

// "So you like green more than blue now?"

// In this example, green is the new value and blue is the old value.

// Make the background color (of all favoriteColor radio buttons) 
// the newly selected favoriteColor

var oldFav;
var newFav;
var c = document.querySelectorAll("input[name=favoriteColor]");

function favoriteColor() {
    var count = 0;
    for (var i = 0; i < c.length; i++) {
        c[i].addEventListener("change", function (event) {
            // Will check if the first color is being selected
            if (count === 0) {
                count++;
                oldFav = event.target.value;
            } else {
                var oldVal = oldFav;
                newFav = event.target.value;
                alert("So you like " + newFav + " more than " + oldVal + " now?");
                oldFav = newFav;
            }

            // Set background color of radio button
            for (var j = 0; j < c.length; j++) {
                c[j].style.backgroundColor = oldFav;
            }
        });
    }
}

// 9. Show/Hide Event

// NOTE: Write unobtrusive Javascript

// When user hovers over an employees name:

// Hide the name if shown.
// Show the name if hidden.

function employeeHover() {
    var emps = document.getElementsByClassName("empName");
    //var emps = document.querySelectorAll("table>tr>[name=empName]");
    for (var i = 0; i < emps.length; i++) {
        emps[i].addEventListener("mouseover", function (event) {
            if (event.target.style.color === "black") {
                event.target.style.color = "white";
            } else {
                event.target.style.color = "black";
            }
        });
    }
};

// 10. Current Time

// Regarding this element:
// 	<h5 id="currentTime"></h5>

// Show the current time in this element in this format: 9:05:23 AM

// The time should be accurate to the second without having to reload the page.

function currentTimeFunc() {
    var currentT = new Date();

    var AMorPM;
    var hours = currentT.getHours();
    var minutes = currentT.getMinutes();
    var seconds = currentT.getSeconds();

    // Putting minutes and seconds into correct format
    if (hours > 12) {
        AMorPM = "PM";
        hours -= 12;
    } else {
        AMorPM = "AM"
    }
    if (minutes < 10) {
        minutes = "0" + minutes;
    }
    if (seconds < 10) {
        seconds = "0" + seconds;
    }

    // Show correct time in currentTime element
    document.getElementById("currentTime").innerHTML = hours + ":" + minutes + ":" + seconds + " " + AMorPM;

    // Make an interval that referesh the value every second
    setInterval(function () {
        currentTimeFunc();
    }, 1000);
};

// 11. Delay
// Regarding this element:
// 	s
// <p id="helloWorld">Hello, World!</p>

// Three seconds after a user clicks on this element, change the text to a random color.

function randomColor() {
    var randomColor = "#";

    // Hex values
    var values = "0123456789ABCDEF";
    // Randomly generate through values
    for (var i = 0; i < 6; i++) {
        randomColor += values[Math.floor(Math.random() * 16)];
    }

    return randomColor;
};

function delayHelloWorld() {
    var h = document.getElementById("helloWorld");

    h.addEventListener("click", function () {
        //  Waits 3 seconds
        setTimeout(function () {
            h.style.color = randomColor();
        }, 3000);
    });
};

// 12. Walk the DOM

// Define function walkTheDOM(node, func)

// This function should traverse every node in the DOM. 
// Use recursion.

// On each node, call func(node).

// Will store nodes traversed
var n = [];
var count = 0;

function walkTheDOM(node, func) {
    var kids = node.children;

    if (kids != null) {
        for (var i = 0; i < kids.length; i++) {
            n[count] = kids[i].tagName;
            count++
            walkTheDOM(kids[i], func);
        }
    }
};


// Filler function for #12
function func(node) {
}

// Extra functions

function getChildren() {
    var s = document.querySelectorAll("select[name=skills] > option");

    for (var i = 0; i < s.length; i++) {
        s[i].addEventListener("change", function () {
            alert("Working");
            //alert("Are you sure " + s[i].textContent + "is one of your skills?!");
        });
        console.log(s[i].textContent);
    }
}