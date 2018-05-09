function calc(num1, mod, num2) {
	if(Number.isInteger(num1)) {
		if(Number.isInteger(num2)) {
			if(mod == '+') {
				return num1 + num2;
			} else if (mod == '-') {
				return num1 - num2;
			} else if (mod == '/') {
				return num1 / num2;
			} else if (mod == '*') {
				return num1 * num2;
			} else
				return "Modifier is not correct!"
		} else
			return "num2 is NOT a valid Number!"
	} else
		return "num1 is NOT a valid Number!"
}