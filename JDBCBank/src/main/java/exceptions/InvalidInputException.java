package exceptions;

public class InvalidInputException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Is called whenever user input is invalid
	 */

	public InvalidInputException() {
		super("Invalid Input! Please try again!");
	}
}
