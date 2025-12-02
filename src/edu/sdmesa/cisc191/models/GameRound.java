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
	public static final int TRIES_PER_ROUND = 6;
	private static final int SCORE_PER_TRY = 10;
	private Word word;
	private int triesRemaining = TRIES_PER_ROUND;
	private Set<Character> lettersGuessedCorrect = new HashSet<>();
	private Set<Character> lettersGuessedWrong = new HashSet<>();
	private boolean isRoundOver_ = false;
	private boolean isRoundWon_ = false;
	
	public GameRound(Word word) {
		if (word == null) throw  new IllegalArgumentException();
		
		this.word = word;
	}
	
	public GameRound(GameRound other) {
		if (other == null) throw new IllegalArgumentException();
		
		// members should be in a valid state
		word = new Word(other.word);
		triesRemaining = other.triesRemaining;
		lettersGuessedCorrect = new HashSet<>(other.lettersGuessedCorrect);
		lettersGuessedWrong = new HashSet<>(other.lettersGuessedWrong);
	}
	
	public Word getWord() {
		return word;
	}
	
	public void setWord(Word word) {
		if (word == null) throw new IllegalArgumentException();
		
		this.word = word;
	}
	
	public void makeGuess(char letter) {
		if (isRoundOver_) {
			return;
		}
		
		letter = Character.toLowerCase(letter);
		
		if (!(letter >= 'a' && letter <= 'z')) return;
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
		
		// update wining/losing conditions
		if (triesRemaining == 0) {
			isRoundOver_ = true;
		}
		else if (triesRemaining > 0 && word.isMatch(lettersGuessedCorrect)) {
			isRoundOver_ = true;
			isRoundWon_ = true;
		}
	}
	
	public boolean isRoundOver() {
		return isRoundOver_;
	}
	
	public boolean isRoundWon() {
		return isRoundWon_;
	}
	
	public int getScore() {
		if (isRoundOver_ && !isRoundWon_) {
			return 0;
		}
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
