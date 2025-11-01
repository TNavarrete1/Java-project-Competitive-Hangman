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
	private int score;
	
	public Word() {}
	
	public Word(String text, String hint, int score) {
		this.text = text;
		this.hint = hint;
		this.score = score;
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
}
