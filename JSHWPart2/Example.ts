class Creator {
    greeting: string;
    constructor(message: String) {
        this.greeting = message;
    }

    // this keyword to indicate a class member rather than an instance variable
    greet() {
        return this.greeting
    }

}