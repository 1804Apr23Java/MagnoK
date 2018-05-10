var homework = {};

1. Return the nth fibonacci number

f(0) = 0
f(1) = 1
f(10) = 55

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



2. Sort array of integers

f([2, 4, 5, 1, 3, 1]) = [1, 1, 2, 3, 4, 5]

Don't use the Array sort() method... that would be lame.
    * /

homework.sort = function (array) {
    var sorted = [];
    var len = array.length;
    var temp

    // Bubble Sort
    while (true) {
        sorted = array;
        for (i = 0; i < len; i++) {
            if (array[i] < array[i + 1]) {
                //Stays in the same spot
            } else {
                //Swap
                array[i] = temp;
                array[i] = array[i + 1];
                array[i + 1] = temp;
            }
        }
        // Will only break out of loop once the array is sorted
        if (array = sorted) {
            break;
        }
    }
    console.log(array);
    return array;
};



3. Return the factorial of n

f(0) = 1
f(1) = 1
f(3) = 6
    * /
homework.factorial = function (n) {
    if (n == 0 || n == 1) {
        return 1;
    }
    var res = n;
    var i;
    for (i = 1; i < n; i++) {
        res = res * (n - i);
    }
    console.log(res);
    return res;
};



4. Rotate left

Given array, rotate left n times and return array

f([1, 2, 3, 4, 5], 1) = [2, 3, 4, 5, 1]
f([1, 2, 3, 4, 5], 6) = [2, 3, 4, 5, 1]
f([1, 2, 3, 4, 5], 3) = [4, 5, 1, 2, 3]

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
    console.log(array);
    return array;
};



5. Balanced Brackets

A bracket is any one of the following: (, ), {, }, [, or]

The following are balanced brackets:
     ()
    ()()
    (())
    ({[]})

The following are NOT balanced brackets:
(
)
    (()
        ([)]

    Return true if balanced
Return false if not balanced
    * /
homework.balancedBrackets = function (bracketsString) {
    // If the length of the string is less than 2 or and odd number
    if (bracketsString.length % 2 != 0) {
        return false;
    }

    var len = bracketsString.length;
    var i;
    var arr = bracketsString.split("");
    // Will identify number of pairs
    var pairs = arr.length/2;

    // Will be filled with distance from middle pair
    var dist = 1;

    console.log(arr);
    console.log(pairs);

    for (i = 0; i < arr.length; i++) {
        // If anything else besides a bracket appears it will return false
        switch (arr[i]) {
            case '(':
                break;
            case ')':
                break;
            case '[':
                break;
            case ']':
                break;
            case '{':
                break;
            case '}':
                break;
            default:
                return false;
        }

        //

        // Multiple grouped brackets
        if (bracketsString.charAt(i) == bracketsString.charAt(i + 1)) {
            // Check pairs if they match
            for (var j = 0; j < i + 1; j++) {
                if (bracketsString.charAt(i - dist) != bracketsString.charAt(i + dist + 1)) {
                    return false;
                }
                dist++;
            }
            // Will only return true if all pairs match
            return true;
        }

        // If there are no balanced brackets
        return false;
    }

    // Will only return true once all pairs are identified
    if(pairs != 0) {
        return false;
    } else {
        return true;
    }
};









