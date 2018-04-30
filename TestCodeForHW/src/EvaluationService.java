import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j = 0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {

		String acronym = "";
		char l;

		for (int i = 0; i < phrase.length(); i++) {
			l = phrase.charAt(i);
			if (i == 0) {
				// For first letter in the acronym
				if (l == ' ') {
					// Checks if the first character in the string is a blank and then adds the next
					// letter
					acronym += Character.toUpperCase(phrase.charAt(i + 1));
				}
				// If it isn't a blank then add the letter
				acronym += Character.toUpperCase(phrase.charAt(i));
			}
			if (l == ' ' || l == '-') {
				// If the current character is a space or dash go to the next letter and add to
				// acronym
				acronym += Character.toUpperCase(phrase.charAt(i + 1));
			}
		}

		return acronym;
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
			if (sideOne == sideTwo && sideTwo == sideThree)
				return true;
			else
				return false;
		}

		public boolean isIsosceles() {
			if (sideOne == sideTwo || sideTwo == sideThree || sideOne == sideThree)
				return true;
			else
				return false;
		}

		public boolean isScalene() {
			if (sideOne == sideTwo || sideTwo == sideThree || sideOne == sideThree)
				return false;
			else
				return true;
		}

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
	public int getScrabbleScore(String string) {
		int score = 0;
		char c;
		for (int i = 0; i < string.length(); i++) {
			c = string.charAt(i);
			switch (c) {
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
			case 'A':
			case 'E':
			case 'I':
			case 'O':
			case 'U':
			case 'L':
			case 'N':
			case 'R':
			case 'S':
			case 'T':
				score += 1;
				break;
			case 'd':
			case 'g':
			case 'D':
			case 'G':
				score += 2;
				break;
			case 'b':
			case 'c':
			case 'm':
			case 'p':
			case 'B':
			case 'C':
			case 'M':
			case 'P':
				score += 3;
				break;
			case 'f':
			case 'h':
			case 'v':
			case 'w':
			case 'y':
			case 'F':
			case 'H':
			case 'V':
			case 'W':
			case 'Y':
				score += 4;
				break;
			case 'k':
			case 'K':
				score += 5;
				break;
			case 'j':
			case 'x':
			case 'J':
			case 'X':
				score += 8;
				break;
			case 'q':
			case 'z':
			case 'Q':
			case 'Z':
				score += 10;
				break;
			default:
			}
		}
		return score;
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
		String cleanNumber = "";

		for (int i = 0; i < string.length(); i++) {
			switch (string.charAt(i)) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				cleanNumber += string.charAt(i);
				break;
			case '+':
			case ' ':
			case '.':
			case '(':
			case ')':
			case '-':
				break;
			default:
				throw new IllegalArgumentException("Non-numeric Entry has been entered!");
			}
		}
		if (cleanNumber.length() > 11)
			throw new IllegalArgumentException("Invalid Number. More than 11 digits have been entered!");

		return cleanNumber;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {

		String cleanString = "";

		for (int i = 0; i < string.length(); i++) {
			switch (string.charAt(i)) {
			case ',':
				// Replace comma with spaces
				cleanString += " ";
				break;
			case '\t':
			case '\b':
			case '\n':
			case '\r':
			case '\f':
			case '\'':
			case '\"':
			case '\\':
				// Handles Control Characters by passing over them when creating cleanString
				break;
			default:
				cleanString += string.charAt(i);
				break;
			}
		}

		String[] word = cleanString.split(" ");
		Map<String, Integer> words = new HashMap<String, Integer>();

		for (String w : word) {
			if (words.containsKey(w)) {
				// If a word exists in the Map
				words.put(w, words.get(w) + 1);
			} else {
				// If a word does not exist in the Map
				words.put(w, 1);
			}
		}

		return words;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {

			int first = 0;
			int last = sortedList.size() - 1;

			if (sortedList.get(0) instanceof String) {

				// For A list of Strings
				int indVal = 0;
				int numSearch = Integer.parseInt(t.toString());
				int middle;

				while (first <= last) {
					middle = (first + last) / 2;
					indVal = Integer.parseInt((String) sortedList.get(middle));
					if (indVal == numSearch) {
						// If the middle index is the key
						return middle;
					} else if (indVal < numSearch) {
						// If the middle index is less than the key value
						first = middle + 1;
					} else if (indVal > numSearch) {
						// If the middle index is greater than the key value
						last = middle - 1;
					}
				}
			} else if (sortedList.get(0) instanceof Integer) {

				int middle;
				int numSearch = (int) t;

				while (first <= last) {
					middle = (first + last) / 2;
					if ((int) sortedList.get(middle) == numSearch) {
						return middle;
					} else if ((int) sortedList.get(middle) < numSearch) {
						first = middle + 1;
					} else if ((int) sortedList.get(middle) > numSearch) {
						last = middle - 1;
					}
				}
			}

			// Element not found
			return -1;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		String pigLatin = "";
		String[] words = string.split(" ");
		String pigWord = "";

		for (int i = 0; i < words.length; i++) {

			switch (words[i].charAt(0)) {
			// Vowels
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
				words[i] += "ay";
				break;
			// Consonant Clusters
			case 's':
				// Two lettered s clusters
				if (words[i].charAt(1) == 'm' || words[i].charAt(1) == 'n' || words[i].charAt(1) == 't'
						|| words[i].charAt(1) == 'w' || words[i].charAt(1) == 'k' || words[i].charAt(1) == 'l'
						|| words[i].charAt(1) == 'p') {
					if (words[i].charAt(2) != 'h') {
						for (int j = 0; j < words[i].length() - 2; j++) {
							pigWord += words[i].charAt(j + 2);
						}
						for (int k = 0; k < 2; k++) {
							pigWord += words[i].charAt(k);
						}
						pigWord += "ay";
						words[i] = pigWord;
						pigWord = "";
						break;
					}
				}
				// Three lettered s clusters
				if ((words[i].charAt(1) == 'p' && words[i].charAt(2) == 'h')
						|| (words[i].charAt(1) == 'h' && words[i].charAt(2) == 'r')
						|| (words[i].charAt(1) == 'c' && words[i].charAt(2) == 'h')) {
					for (int j = 0; j < words[i].length() - 3; j++) {
						pigWord += words[i].charAt(j + 3);
					}
					for (int k = 0; k < 3; k++) {
						pigWord += words[i].charAt(k);
					}
					pigWord += "ay";
					words[i] = pigWord;
					pigWord = "";
					break;
				}
			case 't':
				// Two lettered t clusters
				if (words[i].charAt(1) == 'w' || words[i].charAt(1) == 'r' || words[i].charAt(1) == 'h') {
					for (int j = 0; j < words[i].length() - 2; j++) {
						pigWord += words[i].charAt(j + 2);
					}
					for (int k = 0; k < 2; k++) {
						pigWord += words[i].charAt(k);
					}
					pigWord += "ay";
					words[i] = pigWord;
					pigWord = "";
					break;
				}
				// Three lettered t clusters
				if ((words[i].charAt(1) == 'h' && words[i].charAt(2) == 'w')
						|| (words[i].charAt(1) == 'h' && words[i].charAt(2) == 'r')) {
					for (int j = 0; j < words[i].length() - 3; j++) {
						pigWord += words[i].charAt(j + 3);
					}
					for (int k = 0; k < 3; k++) {
						pigWord += words[i].charAt(k);
					}
					pigWord += "ay";
					words[i] = pigWord;
					pigWord = "";
					break;
				}
			case 'd':
				// d clusters
				if (words[i].charAt(1) == 'w' || words[i].charAt(1) == 'r') {
					for (int j = 0; j < words[i].length() - 2; j++) {
						pigWord += words[i].charAt(j + 2);
					}
					for (int k = 0; k < 2; k++) {
						pigWord += words[i].charAt(k);
					}
					pigWord += "ay";
					words[i] = pigWord;
					pigWord = "";
					break;
				}
			case 'q':
				if (words[i].charAt(1) == 'u') {
					for (int j = 0; j < words[i].length() - 2; j++) {
						pigWord += words[i].charAt(j + 2);
					}
					for (int k = 0; k < 2; k++) {
						pigWord += words[i].charAt(k);
					}
					pigWord += "ay";
					words[i] = pigWord;
					pigWord = "";
					break;
				}
			case 'c':
			case 'p':
			case 'f':
			case 'g':
			case 'b':
				// All use 'r' or 'l' as second letter
				if (words[i].charAt(1) == 'r' || words[i].charAt(1) == 'l') {
					for (int j = 0; j < words[i].length() - 2; j++) {
						pigWord += words[i].charAt(j + 2);
					}
					for (int k = 0; k < 2; k++) {
						pigWord += words[i].charAt(k);
					}
					pigWord += "ay";
					words[i] = pigWord;
					pigWord = "";
					break;
				}
			default:
				// All other consonants
				for (int j = 0; j < words[i].length() - 1; j++) {
					pigWord += words[i].charAt(j + 1);
				}
				pigWord += words[i].charAt(0);
				pigWord += "ay";
				words[i] = pigWord;
				pigWord = "";

			}

			if (i == 0) {
				// For first word
				pigLatin += words[i];
				continue;
			}
			pigLatin += " " + words[i];
		}

		return pigLatin;
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		int sum = 0;
		String num = Integer.toString(input);
		int exp = num.length();

		for (int i = 0; i < num.length(); i++) {
			sum += Math.pow(Character.getNumericValue(num.charAt(i)), exp);
		}

		if (sum == input) {
			return true;
		} else
			return false;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		List<Long> primes = new ArrayList<Long>();

		// Divisible by 2
		while (l % 2 == 0) {
			primes.add((long) 2);
			l /= 2;
		}

		// Divisible by other prime numbers
		for (int i = 3; i <= Math.sqrt((long) l); i += 2) {
			// Primes will be found until square root of number
			while (l % i == 0) {
				primes.add((long) i);
				l /= i;
			}
		}

		// Prime number left after while loop
		if (l > 2)
			primes.add((long) l);

		return primes;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			String deciphered = "";

			// lowercase
			String alphabet = "abcdefghijklmnopqrstuvwxyz";
			String cipherAlphabet = "";
			// UPPERCASE
			String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String upperCipherAlphabet = "";

			// Shift both alphabets according to key
			for (int j = 0; j < alphabet.length() - key; j++)
				cipherAlphabet += alphabet.charAt(j + key);
			for (int k = 0; k < key; k++)
				cipherAlphabet += alphabet.charAt(k);
			for (int j = 0; j < upperAlphabet.length() - key; j++)
				upperCipherAlphabet += upperAlphabet.charAt(j + key);
			for (int k = 0; k < key; k++)
				upperCipherAlphabet += upperAlphabet.charAt(k);

			// Decipher string
			for (int i = 0; i < string.length(); i++) {
				boolean letter = false;
				for (int j = 0; j < alphabet.length(); j++) {
					if (string.charAt(i) == alphabet.charAt(j)) {
						deciphered += cipherAlphabet.charAt(j);
						// Move on to next character and indicate that a letter has been found
						letter = true;
						break;
					} else if (string.charAt(i) == upperAlphabet.charAt(j)) {
						deciphered += upperCipherAlphabet.charAt(j);
						letter = true;
						break;
					}

				}
				// If character is a space or punctuation or anything else than a letter
				if (letter == false) {
					deciphered += string.charAt(i);
				} else
					letter = false;
			}

			return deciphered;
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		List<Long> primes = new ArrayList<Long>();
		// Will be used to indicate when we have reached the nth prime
		// Starts at 2 as first two primes are already added
		int nThPrimeIndicator = 2;
		boolean prime = true;

		if (i <= 0)
			throw new IllegalArgumentException("Invalid input!");

		primes.add((long) 2);
		primes.add((long) 3);

		// Looking for 1st or 2nd prime number
		if (i == 1) {
			return Math.toIntExact(primes.get(i - 1));
		} else if (i == 2) {
			return Math.toIntExact(primes.get(i - 1));
		}

		// Populate list with primes until nth prime is found
		for (int j = 4; j < i * i; j++) {
			for (int k = 2; k < j; k++) {
				if (j % k == 0) {
					// If the number j is divisible by any number leading up to it and will indicate
					// it is not prime
					prime = false;
					break;
				}
			}
			// If the number is a prime the boolean prime would have stayed true
			if (prime) {
				primes.add((long) j);
				nThPrimeIndicator++;
				if (nThPrimeIndicator == i)
					break;
			} else
				prime = true;

		}

		return Math.toIntExact(primes.get(i - 1));
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {

			String encoded = "";

			String alphabet = "abcdefghijklmnopqrstuvwxyz";
			String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String ciphabet = "zyxwvutsrqponmlkjihgfedcba";

			boolean foundLetter = false;
			int blockIndicator = 1;
			int endOfStringIndicator = 0;

			for (int i = 0; i < string.length(); i++) {
				endOfStringIndicator++;

				switch (string.charAt(i)) {
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					// If the character is a number
					encoded += string.charAt(i);
					foundLetter = true;
					break;
				default:
					break;
				}

				// Find index of character in string
				for (int j = 0; j < alphabet.length(); j++) {
					if (string.charAt(i) == alphabet.charAt(j)) {
						// Add character to encoded message
						encoded += ciphabet.charAt(j);
						foundLetter = true;
					} else if (string.charAt(i) == upperAlphabet.charAt(j)) {
						encoded += ciphabet.charAt(j);
						foundLetter = true;
					}

					if (foundLetter) {
						if (blockIndicator % 5 == 0 && endOfStringIndicator != string.length() - 1) {
							encoded += " ";
						}
						foundLetter = false;
						blockIndicator++;
						break;
					}
				}
			}

			return encoded;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {

			String decoded = "";

			String alphabet = "abcdefghijklmnopqrstuvwxyz";
			String ciphabet = "zyxwvutsrqponmlkjihgfedcba";

			for (int i = 0; i < string.length(); i++) {

				switch (string.charAt(i)) {
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					// If the character is a number
					decoded += string.charAt(i);
					break;
				default:
					break;
				}

				// Find index of character in string
				for (int j = 0; j < ciphabet.length(); j++) {
					if (string.charAt(i) == ciphabet.charAt(j)) {
						// Add character to decoded message
						decoded += alphabet.charAt(j);
					}
				}
			}

			return decoded;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {

		int sum = 0;
		int multiplier = 10;

		for (int i = 0; i < string.length(); i++) {
			switch (string.charAt(i)) {
			case '0':
				sum += (0 * multiplier);
				multiplier -= 1;
				break;
			case '1':
				sum += (1 * multiplier);
				multiplier -= 1;
				break;
			case '2':
				sum += (2 * multiplier);
				multiplier -= 1;
				break;
			case '3':
				sum += (3 * multiplier);
				multiplier -= 1;
				break;
			case '4':
				sum += (4 * multiplier);
				multiplier -= 1;
				break;
			case '5':
				sum += (5 * multiplier);
				multiplier -= 1;
				break;
			case '6':
				sum += (6 * multiplier);
				multiplier -= 1;
				break;
			case '7':
				sum += (7 * multiplier);
				multiplier -= 1;
				break;
			case '8':
				sum += (8 * multiplier);
				multiplier -= 1;
				break;
			case '9':
				sum += (9 * multiplier);
				multiplier -= 1;
				break;
			case 'X':
				sum += (10 * multiplier);
				multiplier -= 1;
				break;
			case '-':
				// Skip over dashes
				break;
			default:
				// Invalid Input
				return false;
			}
		}
		if (sum % 11 == 0)
			return true;
		else
			return false;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: Ã�â‚¬ÃŽÂ±ÃŽÂ½
	 * ÃŽÂ³Ã�ï¿½ÃŽÂ¬ÃŽÂ¼ÃŽÂ¼ÃŽÂ±, pan gramma, "every letter") is a sentence using
	 * every letter of the alphabet at least once. The best known English pangram
	 * is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		boolean foundLetter = false;

		for (int i = 0; i < alphabet.length(); i++) {
			for (int j = 0; j < string.length(); j++) {
				// If the character is a space it will move on to the next character in the
				// string
				if (string.charAt(j) == ' ')
					continue;

				if (alphabet.charAt(i) == string.charAt(j)) {
					// Letter exists in sentence goes to next letter
					foundLetter = true;
					break;
				}
			}
			if (!foundLetter) {
				// Once a letter is not found, false will be returned
				return false;
			} else
				foundLetter = false;
		}
		// Will only exit loop if every letter in alphabet is found in sentence
		return true;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {

		// Each Field of time
		int year = 0;
		int month = 0;
		int day = 0;
		int hour = 0;
		int minute = 0;
		int second = 0;

		// Find out if the given Temporal contained the fields of time or not
		if (given.isSupported(ChronoField.YEAR))
			year = given.get(ChronoField.YEAR);
		if (given.isSupported(ChronoField.MONTH_OF_YEAR))
			month = given.get(ChronoField.MONTH_OF_YEAR);
		if (given.isSupported(ChronoField.DAY_OF_MONTH))
			day = given.get(ChronoField.DAY_OF_MONTH);
		if (given.isSupported(ChronoField.HOUR_OF_DAY))
			hour = given.get(ChronoField.HOUR_OF_DAY);
		if (given.isSupported(ChronoField.MINUTE_OF_HOUR))
			minute = given.get(ChronoField.MINUTE_OF_HOUR);
		if (given.isSupported(ChronoField.SECOND_OF_MINUTE))
			second = given.get(ChronoField.SECOND_OF_MINUTE);

		// Add gigaSecond to given temporal according to supported fields of time
		given = LocalDateTime.of(year, month, day, hour, minute, second).plusSeconds(1000000000);

		return given;
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		int sum = 0;
		List<Long> multiples = new ArrayList<Long>();
		boolean multipleExists = false;

		for (int j = 0; j < set.length; j++) {
			for (int k = set[j]; k < i; k++) {
				if (k % set[j] == 0) {
					// Check if multiple exists in list
					for (int l = 0; l < multiples.size(); l++) {
						if (k == multiples.get(l)) {
							multipleExists = true;
							break;
						}
					}
					if (!multipleExists)
						multiples.add((long) k);
					else
						multipleExists = false;
				}
			}
		}

		for (int m = 0; m < multiples.size(); m++) {
			sum += multiples.get(m);
		}

		return sum;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {

		// Check length of String
		if (string.length() <= 1)
			return false;

		// Get skips spaces and checks for invalid characters
		String cleanString = "";
		for (int i = 0; i < string.length(); i++) {
			switch (string.charAt(i)) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				cleanString += string.charAt(i);
				break;
			case ' ':
				break;
			default:
				return false;
			}
		}

		// Populate list of numbers
		List<Integer> nums = new ArrayList<Integer>();
		for (int k = 0; k < cleanString.length(); k++)
			nums.add(Character.getNumericValue(cleanString.charAt(k)));

		// Luhn's Algorithm

		// 1) Double every second digit starting from the right
		// - If greater than 9 subtract 9
		for (int j = nums.size() - 2; j >= 0; j -= 2) {
			nums.set(j, nums.get(j) * 2);
			if (nums.get(j) > 9)
				nums.set(j, nums.get(j) - 9);
		}

		// 2) Sum all digits
		int sum = 0;
		for (int l = 0; l < nums.size(); l++) {
			sum += nums.get(l);
		}

		// 3) If the sum is divisible by 10 then it is valid!
		if (sum % 10 == 0)
			return true;
		else
			return false;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		String[] problem = string.split(" ");
		int result = 0;

		int num1 = 0;
		int num2 = 0;
		String number2 = "";

		num1 = Integer.parseInt(problem[2]);

		if (problem.length == 5) {
			// Second number will be in index 4
			for (int i = 0; i < problem[4].length(); i++) {
				switch (problem[4].charAt(i)) {
				case '-':
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					number2 += problem[4].charAt(i);
					break;
				default:
					break;
				}
			}
			num2 = Integer.parseInt(number2);
		} else {
			// Second number will be in index 5
			for (int i = 0; i < problem[5].length(); i++) {
				switch (problem[5].charAt(i)) {
				case '-':
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					number2 += problem[5].charAt(i);
					break;
				default:
					break;
				}
			}
			num2 = Integer.parseInt(number2);
		}

		if (problem[3].equals("plus"))
			result = num1 + num2;
		else if (problem[3].equals("minus"))
			result = num1 - num2;
		else if (problem[3].equals("multiplied"))
			result = num1 * num2;
		else if (problem[3].equals("divided"))
			result = num1 / num2;

		return result;
	}

}
