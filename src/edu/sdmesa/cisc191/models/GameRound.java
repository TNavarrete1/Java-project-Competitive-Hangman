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
public class GameRound
{
	private static final int SCORE_PER_TRY = 10;
	private Word word = new Word();
	private int triesRemaining = 5;
	private Set<Character> lettersGuessedCorrect = new HashSet<>();
	private Set<Character> lettersGuessedWrong = new HashSet<>();
	
	public GameRound() {}
	
	public GameRound(Word word) {
		this.word = new Word(word);
	}
	
	public GameRound(GameRound other) {
		word = new Word(other.word);
		triesRemaining = other.triesRemaining;
		lettersGuessedCorrect = new HashSet<>(other.lettersGuessedCorrect);
		lettersGuessedWrong = new HashSet<>(other.lettersGuessedWrong);
	}
	
	public void makeGuess(char letter) {
		if (triesRemaining == 0) return; // round is over 
		if (lettersGuessedCorrect.contains(letter) || lettersGuessedWrong.contains(letter)) {
			return; // guess was already made once
		}
		
		if (word.contains(letter)) {
			lettersGuessedCorrect.add(letter);
		}
		else {
			lettersGuessedWrong.add(letter);
			triesRemaining--;
		}
	}
	
	public boolean isRoundOver() {
		return triesRemaining == 0;
	}
	
	public boolean isRoundWon() {
		return triesRemaining > 0 && word.isMatch(lettersGuessedCorrect);
	}
	
	public int getScore() {
		return word.getScore() + triesRemaining * SCORE_PER_TRY;
	}
	
	public int getTriesRemaining() {
		return triesRemaining;
	}
	
	public Set<Character> getLettersGuessedCorrect() {
		return new HashSet<>(lettersGuessedCorrect);
	}
	
	public Set<Character> getLettersGuessedWrong() {
		return new HashSet<>(lettersGuessedWrong);
	}
}
