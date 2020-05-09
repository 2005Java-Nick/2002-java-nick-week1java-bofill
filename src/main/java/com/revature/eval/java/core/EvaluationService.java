package com.revature.eval.java.core;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.sound.midi.Soundbank;

import org.omg.CORBA.PRIVATE_MEMBER;

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
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
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
		
		String string = null;
		StringBuffer s = new StringBuffer("");
		
		for (String stringSplit: phrase.split("[ -]")) {
			String acronym = stringSplit.substring(0,1).toUpperCase();
			s.append(acronym); 
			string = s.toString();
		}
		return string;
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
			if ((sideOne == sideTwo) && (sideOne == sideThree)){
				return true;
			} 
			return false;
		}

		public boolean isIsosceles() {
		     if (((sideOne == sideTwo) && (sideOne != sideThree)) ||
		             ((sideOne == sideThree) && (sideOne != sideTwo)) ||
		             ((sideTwo == sideThree) && (sideTwo != sideOne)))
		            return true;
		     else
			return false;
		}

		public boolean isScalene() {
		     if ((sideOne != sideTwo) && (sideOne != sideThree) && (sideTwo != sideThree))
		          return true;
		     else 
			return false;
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
		String upperCaseWord;
		
		upperCaseWord = string.toUpperCase();
		
		char[] array = upperCaseWord.toCharArray();
		int score = 0;
		
		for (char c: array) {
			score += getValueChar(c);
		}
		
		
		return score;
	}

	private int getValueChar(char c) {
			switch (c) {
			
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
					return 1;
					
				case 'D':
				case 'G':
					return 2;
		
				case 'B':
				case 'C':
				case 'M':
				case 'P':
					return 3;
					
				case 'F':
				case 'H':
				case 'V':
				case 'W':
				case 'Y':
					return 4;
					
				case 'K':
					return 5;
					
				case 'J':
				case 'X':
					return 8;

					
				case 'Q':
				case 'Z':
					return 10;
	
				}
		
		return 0;
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
		
		string = string.replaceAll("[^0-9+]", "");

		
		return string;
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
		int count = 0;
		string = string.replaceAll("[\\n]", "");
		
		Map<String, Integer> map = new HashMap<String, Integer>();  
		 
		if (!string.isEmpty()) {
			for (String stringSplit: string.split("[ ,]")) {
				
				if (map.containsKey(stringSplit)) {
					count = map.get(stringSplit);
					
					map.put(stringSplit, count + 1 );
	
				} else {
					map.put(stringSplit, 1 );
				}
				
			}
		}
		
		return map;
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
			int left = 0, right = sortedList.size(); 
			int middle = left + (right -1 ) / 2;

			while (left <= right) {
				if ( (int) sortedList.get(middle) < (int) sortedList.get((int) t) ){
					left = middle + 1; //the number is on the right side of the list
					
				}	else if ( (int) sortedList.get(middle) == (int) sortedList.get((int) t) ) {
					
					return (int) sortedList.indexOf(middle);
					
				} else {
						right = middle - 1; //the number is on the left side of the list
					}
						middle = left + (right -1 ) / 2;
							
				}
			if (left > right) {
					return 0;
			}
			return 0;
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
	@SuppressWarnings("unlikely-arg-type")
	public String toPigLatin(String string) {
		
		char first = string.charAt(0);
		String[] stringOfWord = string.split("");
		String str=String.valueOf(first);  
		String string2 = "A E I O U a e i o u";

		
		if(string2.contains(str)) {
			String pigLatinWord = string + "ay";
			return pigLatinWord;		
			
		}	
//		more to code 
		return "string";
			
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
		
		int number, digits = 0;
		Double remainder, total = 0.0;
		
        number = input;
        
        while(number != 0)
        {
            number /= 10;
            ++digits;
        }

        number = input;
       
        while (number != 0)
        {
            remainder = (double) number % 10; //gets the last digit on the number
        
            total = total + Math.pow(remainder, digits); 
            number /= 10;
        }
        
        if(total == input)
        	
            return true;
        
        else
		
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
		
		//		Prime Factorization by Trial Division
		List<Long> array = new ArrayList<Long>();
		
		for(int i = 2 ; i < l; i++) {
	         while ( l % i == 0 ) {
	            l = l / i;
	            long iLong = (long) i;
	            array.add(iLong);
	         }
	      }
		
			int lInt = (int) l;
				if ( lInt == 2 ) {
		    	 long lLong = (long) l;
		    	 array.add(lLong); 
	      }

		return array;

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
			
				String s = "";
			  int len = string.length();
			  
			    for(int x = 0; x < len; x++){
			        char c = (char)(string.charAt(x) + key);
			        if (c > 'z')
			            s += (char)(string.charAt(x) - (26-key));
			        else
			            s += (char)(string.charAt(x) + key);
			    }
			    
			return s;
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
			ArrayList<Integer> arr = new ArrayList<Integer>();
		    arr.add(2);
		    arr.add(3);
	
		    int counter = 4;
	
		    while(arr.size() < i) {
		        if(counter % 2 != 0 && counter % 3 != 0) {
		            int temp = 4;
		            while( temp * temp <= counter) {
		                if(counter % temp == 0)
		                    break;
		                temp ++;
		            }
		            if(temp*temp > counter) {
		                arr.add(counter);
		            }
		        }
		        counter++;
		    }
	
		     return arr.get(i-1);
		
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
			
			string = string.toLowerCase();
			string = string.replaceAll("[, .]", "");
			String[] strArray = string.split("");
			String plain = "abcdefghijklmnopqrstuvwxyz";
			String cipher = "zyxwvutsrqponmlkjihgfedcba";
			
			String[] strArrayPlain = plain.split("");
			String[] strArrayCipher = cipher.split("");
			String cipherWord = "";
			
			Map<String, String> cipherMap = new HashMap<>();
			
			for (int i = 0; i < strArrayCipher.length; i++) {
				cipherMap.put(strArrayPlain[i], strArrayCipher[i]);
			}
			
			for (String l: strArray) {
				String letter = cipherMap.get(l);
				cipherWord = cipherWord + letter;				
			}

			if (cipherWord.length() > 5) {
				cipherWord = cipherWord.replaceAll(".....", "$0 ");
			}
			
			return cipherWord;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			// TODO Write an implementation for this method declaration
			return null;
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
		// TODO Write an implementation for this method declaration
		return false;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
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
		string = string.replaceAll(" ", "");

		char[] array = string.toCharArray();
		
		for (char c: array){
			 int val = (int) c;
				if (val < 97 ) {
				}
					else if (val > 122) {
				}
				return false;
		}
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
		// TODO Write an implementation for this method declaration
		return null;
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
			
			
	        for (int j = 0; j < i; j++) {
	            Boolean isMultiple = false;
	
	            for (int num : set) {
	            	if (j % num == 0) {
	            		isMultiple = true;
	            	}
	            }
	
	            if (isMultiple) {
	                sum += j;
	            }
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
		
			string = string.replaceAll("[^0-9.]", "");
			
			String [] stringArray = string.split("");
			
			int[] array = new int [string.length() ];
			
			for(int i = 0; i < string.length() ; i++) {
		         array [i] = Integer.parseInt(stringArray[i]);
		      }
		
//				loop from right to left every two numbers
				for (int i = array.length - 2; i >= 0 ; i = i - 2 ) {
					int num = array[i];
					num = num * 2;  
					if ( num > 9 ) {
						num = num % 10 + num / 10;  
					}
					array[i] = num;
				}

				int sumOfDigits = Arrays.stream(array).sum();
				  
				if( sumOfDigits % 10 == 0 )  
				{
					return true;
				}
			
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
		String pattern = "What\\sis\\s([-]?\\d+)\\s(plus|minus|multiplied\\sby|divided\\sby)\\s([-]?\\d+)\\?";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(string);
        int result = 0;
        while (matcher.find()) {

            int val1 = Integer.parseInt(matcher.group(1));
            int val2 = Integer.parseInt(matcher.group(3));
            String oper = matcher.group(2);
            
            switch (oper) {

                case "plus" : 
                	result =  val1 + val2;
                	break;
                case "minus" : 
                	result =  val1 - val2;
                	break;
                case "multiplied by" : 
                	result = val1 * val2;
                	break;
                case "divided by" : 
                	result =  val1 / val2;
                	break;
                default :
                    break;
            };          
	     }
        return result;
	}       

}
