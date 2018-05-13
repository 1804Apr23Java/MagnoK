// 1. USA
// Define function getUSA()

// Find the html element that contains "USA".

// Print that element's contents.

function getUSA() {
    var text = "USA";
    var tags = document.body.getElementsByTagName("*");

    var cAttr = document.querySelectorAll("[data-customAttr]");
    for (var i = 0; i < cAttr.length; i++) {
        if (cAttr[i].dataset.customattr === "USA") {
            console.log(cAttr[i].textContent);
        }
    }

    for (var i = 0; i < tags.length; i++) {
        var n = tags[i].textContent.search(text);
        if (n != 1) {
            //alert(tags[i].textContent);
            console.log(tags[i].textContent);
            break;
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
}

// 3. Click Here

// Define function getAnchorChildren()

// Find all anchor elements with a <span> child.

// Print the contents of <span>

function getAnchorChildren() {
    var anchorChildren = document.querySelectorAll("A>span");
    for (var i = 0; i < anchorChildren.length; i++) {
        console.log(anchorChildren[i].textContent);
    }
}

// 4. Hobbies
// Define function getHobbies()	
// Find all checked options in the 'skills' select element.

// Print the value and the contents.

function getHobbies() {
    var h = document.getElementsByName("hobbies")[0];

    // Will refresh value and will print currently selected option every 3 msec.
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

}

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
}

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
    var num1 
}


window.onload = function () {
    //getUSA();
    //getPeopleInSales();
    //getAnchorChildren();
    //getHobbies();
    getCustomAttribute();
}