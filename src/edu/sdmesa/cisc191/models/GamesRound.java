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
import java.util.Set;

/**
 * Purpose: The reponsibility of GamesRound is ...
 *
 * GamesRound is-a ...
 * GamesRound is ...
 */
public class GamesRound
{
	private Word word = new Word();
	private int triesRemaining = 5;
	private int score;
	private Set<Character> lettersGuessedCorrect = new HashSet<>();
	private Set<Character> lettersGuessedWrong = new HashSet<>();
	
	public GamesRound(Word word) {
		this.word = word;
	}
	
	public boolean makeGuess(char letter) {
		// TODO: update state
		return false;
	}
	
	public boolean isRoundOver() {
		return false;
	}
	
	public boolean isRoundWon() {
		return false;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getTriesRemaining() {
		return triesRemaining;
	}
	
	public Set<Character> getLettersGuessedCorrect() {
		return lettersGuessedCorrect;
	}
	
	public Set<Character> getLettersGuessedWrong() {
		return lettersGuessedWrong;
	}
}
