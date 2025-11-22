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
	private String text = "";
	private String hint = "";
	private int score = 0;
	private Set<Character> charSet = new HashSet<>();
	
	public Word() {}
	
	public Word(String text, String hint) {
		setText(text);
		setHint(hint);
	}
	
	public Word(Word other) {
		if (other == null) return;
		
		text = other.text;
		hint = other.hint;
		score = other.score;
		charSet = new HashSet<>(other.charSet);
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		if (text == null) return;
		this.text = text;
		score = calculateScore(text);
		charSet = extractCharSet(text);
	}
	
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		if (hint == null) return;
		this.hint = hint;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore() {
		score = Math.max(0, score);
	}
	
	public boolean contains(char letter) {
		return charSet.contains(letter);
	}
	
	public boolean isMatch(Set<Character> letters) {
		return charSet.containsAll(letters) && charSet.size() == letters.size();
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
	
	private int calculateScore(String text) {
		if (text == null) return 0;
		
		String[] parts = text.split(" ");
		int score = 0;
		for (String part : parts) {
			score += part.length();
		}
		
		return score;
	}
	
	private Set<Character> extractCharSet(String text) {
		if (text == null) return new HashSet<>();
		
		Set<Character> set = new HashSet<>();
		String[] parts = text.split(" ");
		for (String part : parts) {
			for (char letter : part.toCharArray()) {
				set.add(letter);
			}
		}
		
		return set;
	}
}
