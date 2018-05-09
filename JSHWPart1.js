var homework = {};

1. Return the nth fibonacci number

f(0) = 0
f(1) = 1
f(10) = 55

homework.fibonacci = function(n){
    var nums = [];
    nums[0] = 0;
    nums[1] = 1;
    var i;
    for(i = 2; i <= n; i++) {
        nums[i] = nums[i-1] + nums[i-2];
    }
    return nums[n];
};



2. Sort array of integers

f([2,4,5,1,3,1]) = [1,1,2,3,4,5]

Don't use the Array sort() method... that would be lame.
*/

homework.sort = function(array) {
    var sorted = [];
    var len = array.length;
    var temp

    // Bubble Sort
    while(true) {
        sorted = array;
        for(i = 0; i < len; i++) {
            if(array[i] < array[i+1]) {
                //Stays in the same spot
            } else {
                //Swap
                array[i] = temp;
                array[i] = array[i+1];
                array[i+1] = temp;
            }
        }
        // Will only break out of loop once the array is sorted
        if(array = sorted) {
            break;
        }
    }
    return array;
};


3. Return the factorial of n

f(0) = 1
f(1) = 1
f(3) = 6
*/
homework.factorial = function(n){
    if(n == 0 || n == 1) {
        return 1;
    }
    var res = n;
    var i;
    for(i = 1; i < n; i++) {
        res = res*(n-i);
    }
    return res;
};

4. Rotate left

Given array, rotate left n times and return array

f([1,2,3,4,5], 1) = [2,3,4,5,1]
f([1,2,3,4,5], 6) = [2,3,4,5,1]
f([1,2,3,4,5], 3) = [4,5,1,2,3]

*/
homework.rotateLeft = function(array, n) {
    var shift = [];
    var i;
    for(i = 0; array.length; i++) {
        shift[]
    }
};









