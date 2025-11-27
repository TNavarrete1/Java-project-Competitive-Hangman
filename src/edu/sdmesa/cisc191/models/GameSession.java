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
* Version: 2025-11-13
*/
package edu.sdmesa.cisc191.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Purpose: The reponsibility of GameSession is ...
 *
 * GameSession is-a ...
 * GameSession is ...
 */
public class GameSession
{
	private final Player player;
	private final String category;
	private int score = 0;
	private int hintsRemaining = 3;
	private final List<GameRound> roundHistory = new ArrayList<>();
	
	// members needed for efficient random word bag
	private final List<Word> words = new ArrayList<>(); // available words
	private int size = 0; // window size I can use to get random word (available words window)
	private final Random rand = new Random();
	
	public GameSession(Player player,String category, List<Word> words) {
		if (player == null) throw new IllegalArgumentException("Player cannot be null");
		if (category == null || category.isEmpty()) throw new IllegalArgumentException("Category cannot be null or empty");
		if (words == null || words.isEmpty()) throw new IllegalArgumentException("Word list cannot be null or empty");
		
		this.player = new Player(player);
		this.category = category;
		this.words.addAll(words);
		size = words.size();
	}
	
	public GameSession(Player player, String category, List<Word> words, int hintsRemaining) {
		this(player, category, words);
		this.hintsRemaining = Math.max(0, Math.min(5, hintsRemaining)); // range is 0-5
	}
	
	public Player getPlayer() {
		return new Player(player);
	}
	
	public String getCategory() {
		return category;
	}
	
	public int getScore() {
		return score;
	}
	
	public List<GameRound> getRoundHistory() {
		List<GameRound> rounds = new ArrayList<>();
		for (GameRound round : roundHistory) {
			rounds.add(new GameRound(round));
		}
		
		return Collections.unmodifiableList(rounds);
	}
	
	public void addRound(GameRound round) {
		roundHistory.add(new GameRound(round));
		score += round.getScore();
	}
	
	public Word getNextWord() {
		if (size == 0) { // no available words
			return null;
		}
		
		int randomIndex = rand.nextInt(size);
		Word randomWord = words.get(randomIndex);
		/* move random word into excluded words memory space.
		 * swap random word with last word in available words window,
		 * then shrink window size. Prevents the random word from 
		 * being used used in following rounds.
		 */
		if (size > 1) {
			Word lastWord = words.get(size - 1);
			words.set(size - 1, randomWord);
			words.set(randomIndex, lastWord);
		}
		size--;
		
		return randomWord;
	}
	
	// can be used to determine if game session is done
	public boolean hasMoreWords() {
		return size > 0;
	}
	
	public void useHint() {
		if (hintsRemaining == 0) {
			return;
		}
		
		hintsRemaining--;
	}
}
