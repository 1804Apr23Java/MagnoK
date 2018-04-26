
public class HW1 {

	public static void main(String[] args) {
		
		//NUMBER 1&2
		//String p = "Portable Network Graphics";
		//System.out.println(acronym(p));
		//System.out.println(reverse(p));
		
		//NUMBER 3
		//Triangle t = new Triangle(2,2,4);
		//System.out.println(t.getSideOne());
		//System.out.println(t.getSideTwo());
		//System.out.println(t.getSideThree());
		//System.out.println(t.isEquilateral());
		//System.out.println(t.isIsosceles());
		//System.out.println(t.isScalene());
		
		//NUMBER 4
		//String s = "cabbage";
		//System.out.println(getScrabbleScore(s));
		
		//NUMBER 5
	}
	
	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		// TODO Write an implementation for this method declaration
		return null;
	}
	
	
	
	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public static int getScrabbleScore(String string) {
		int score = 0;
		char c;
		for(int i=0; i < string.length(); i++) {
			c = string.charAt(i);
			switch(c) {
				case 'a':
				case 'e':
				case 'i':
				case 'o':
				case 'u':
				case 'l':
				case 'n':
				case 'r':
				case 's':
				case 't':
					score += 1;
					break;
				case 'd':
				case 'g':
					score += 2;
					break;
				case 'b':
				case 'c':
				case 'm':
				case 'p':
					score += 3;
					break;
				case 'f':
				case 'h':
				case 'v':
				case 'w':
				case 'y':
					score += 4;
					break;
				case 'k':
					score += 5;
					break;
				case 'j':
				case 'x':
					score += 8;
					break;
				case 'q':
				case 'z':
					score += 10;
					break;
				default :
			}
		}
		return score;
	}
	
	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			if(sideOne == sideTwo && sideTwo == sideThree)
				return true;
			else
				return false;
		}

		public boolean isIsosceles() {
			if(sideOne == sideTwo || sideTwo == sideThree || sideOne == sideThree)
				return true;
			else
				return false;
		}

		public boolean isScalene() {
			if(sideOne == sideTwo || sideTwo == sideThree || sideOne == sideThree)
				return false;
			else
				return true;
		}

	}
	
	// NUMBER 1
	public static String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	// NUMBER 2
	public static String acronym(String phrase) {
		char[] acronym = new char[phrase.length()];
		char l = phrase.charAt(0);
		if(l == ' ') {
			//Do nothing (If phrase input begins with a space by accident)
		} else
			acronym[0] = l;
		for (int i = 0, j = 1; i < phrase.length(); i++) {
			l = phrase.charAt(i);
			if(l == ' ') {
				acronym[j] = phrase.charAt(i+1);
				j++;
			}
		}
		return new String(acronym);
	}

}
