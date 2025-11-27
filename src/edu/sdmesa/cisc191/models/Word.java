/**
* Lead Author(s):
* @author thomas; student ID
* @author Full name; student ID
* <<Add additional lead authors here>>
*
* Other Contributors:
* Full name; student ID or contact information if not in class
* <<Add additional contributors (mentors, tutors, friends) here, with contact information>>
*
* References:
* Morelli, R., & Walde, R. (2016).
* Java, Java, Java: Object-Oriented Problem Solving
* https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
*
* <<Add more references here>>
*
* Version: 2025-10-31
*/
package edu.sdmesa.cisc191.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Purpose: The reponsibility of Word is ...
 *
 * Word is-a ...
 * Word is ...
 */
public class Word
{
	private final String text;
	private final String hint;
	private final int score;
	private final Set<Character> charSet = new HashSet<>();;
	
	public Word(String text, String hint) {
		if (text == null || text.isEmpty()) throw new IllegalArgumentException("Word text cannot be null or empty");
		if (hint == null || hint.isEmpty()) throw new IllegalArgumentException("Word hint cannot be null or empty");	
		
		this.text = text;
		this.hint = hint;
		int charLength = 0;
		for (char c : text.toCharArray()) {
			c = Character.toLowerCase(c);
			if (c >= 'a' && c <= 'z') {
				charSet.add(c);
				charLength++;
			}
		}
		score = charLength;
	}
	
	public Word(Word other) {
		if (other == null) throw new IllegalArgumentException("Word object cannot be null");
		
		text = other.text;
		hint = other.hint;
		score = other.score;
		charSet.addAll(other.charSet);
	}
	
	public String getText() {
		return text;
	}
	
	public String getHint() {
		return hint;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean contains(char letter) {
		letter = Character.toLowerCase(letter);
		return charSet.contains(letter);
	}
	
	public boolean isMatch(Set<Character> letters) {
		return charSet.size() == letters.size() && letters.containsAll(charSet);
	}
	
	@Override
	public String toString() {
		return text + "|" + hint + "|" + score;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Word other = (Word) obj;
		return text.equals(other.getText());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(text);
	}
}
