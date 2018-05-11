// var homework = {};

// 1. Return the nth fibonacci number

// f(0) = 0
// f(1) = 1
// f(10) = 55

homework.fibonacci = function (n) {
    var nums = [];
    nums[0] = 0;
    nums[1] = 1;
    var i;
    for (i = 2; i <= n; i++) {
        nums[i] = nums[i - 1] + nums[i - 2];
    }
    console.log(nums[n]);
    return nums[n];
};



// 2. Sort array of integers

// f([2, 4, 5, 1, 3, 1]) = [1, 1, 2, 3, 4, 5]

// Don't use the Array sort() method... that would be lame.
//     * /

homework.sort = function (array) {
    var sorted = true;
    var temp;

    // Bubble Sort
    while (true) {
        for (i = 0; i < array.length; i++) {
            if (array[i] > array[i + 1]) {
                //Swap
                temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                sorted = false;
            } 
            // Stay in same spot otherwise
        }
        // Will only break out of loop once the array is sorted
        if (sorted) {
            break;
        }
        sorted = true;
    }
    return array;
};



// 3. Return the factorial of n

// f(0) = 1
// f(1) = 1
// f(3) = 6

homework.factorial = function (n) {
    if (n == 0 || n == 1) {
        return 1;
    }
    var res = n;
    var i;
    for (i = 1; i < n; i++) {
        res = res * (n - i);
    }
    return res;
};



// 4. Rotate left

// Given array, rotate left n times and return array

// f([1, 2, 3, 4, 5], 1) = [2, 3, 4, 5, 1]
// f([1, 2, 3, 4, 5], 6) = [2, 3, 4, 5, 1]
// f([1, 2, 3, 4, 5], 3) = [4, 5, 1, 2, 3]

homework.rotateLeft = function (array, n) {
    var temp;
    var i, j;
    var len = array.length;

    for (i = 0; i < n; i++) {
        // first value in temp
        temp = array[0];
        for (j = 0; j < len - 1; j++) {
            // Move values to the left
            array[j] = array[j + 1];
        }
        // Put temp value at the end of array
        array[len - 1] = temp;
    }
    return array;
};



// 5. Balanced Brackets

// A bracket is any one of the following: (, ), {, }, [, or]

// The following are balanced brackets:
// ()
//     ()()
//     (())
//     ({[]})

// The following are NOT balanced brackets:
// (
// )
//     (()
//         ([)]

//     Return true if balanced
// Return false if not balanced

homework.balancedBrackets = function (bracketsString) {
    // If the length of the string is less than 2 or and odd number
    if (bracketsString.length % 2 != 0) {
        return false;
    }

    // Will store indicies of pairs
    var pArr = [];

    // Will identify number of pairs
    var pairs = bracketsString.length / 2;

    for (var i = 0; i < bracketsString.length; i++) {
        // If anything else besides a bracket appears it will return false
        switch (bracketsString.charAt(i)) {
            case '(':
                // Match pairs that are next to each other
                if (bracketsString.charAt(i + 1) == ')') {
                    pairs -= 1;
                    pArr += i;
                    pArr += i + 1;
                }
                break;
            case ')':
                break;
            case '[':
                // Match pairs that are next to each other
                if (bracketsString.charAt(i + 1) == ']') {
                    pairs -= 1;
                    pArr += i;
                    pArr += i + 1;
                }
                break;
            case ']':
                break;
            case '{':
                // Match pairs that are next to each other
                if (bracketsString.charAt(i + 1) == '}') {
                    pairs -= 1;
                    pArr += i;
                    pArr += i + 1;
                }
                break;
            case '}':
                break;
            default:
                return false;
        }
    }

    // Will only return true once all pairs are identified
    if (pairs == 0) {
        return true;
    }

    // Check for nested brackets
    // Will be filled with distance for farthest pair
    var dist = 3 + (2 * (pairs-1));
    for (var k = 0; k < bracketsString.length; k++) {
        // Skips over checked pairs
        if (pArr.includes(k)) {
            continue;
        }

        switch (bracketsString.charAt(k)) {
            case '(':
                if (bracketsString.charAt(k + dist) == ')') {
                    pairs -= 1;
                    dist -= 2;
                } else { return false; }
                break;
            case '[':
                if (bracketsString.charAt(k + dist) == ']') {
                    pairs -= 1;
                    dist -= 2;
                } else { return false; }
                break;
            case '{':
                if (bracketsString.charAt(k + dist) == '}') {
                    pairs -= 1;
                    dist -= 2;
                } else { return false; }
                break;
            default:
        }

        if (pairs == 0) {
            return true;
        }
    }

    // If there are pairs left it will return false
    return false;
};









