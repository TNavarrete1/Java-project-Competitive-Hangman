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
* Version: 2025-11-14
*/
package edu.sdmesa.cisc191.controllers;

import edu.sdmesa.cisc191.events.GameRoundEvents;
import edu.sdmesa.cisc191.events.GameSessionEvents;
import edu.sdmesa.cisc191.models.GameRound;
import edu.sdmesa.cisc191.models.Word;
import edu.sdmesa.cisc191.views.GameRoundView;
import java.lang.IllegalStateException;

import javax.swing.JOptionPane;

/**
 * Purpose: The reponsibility of GameRoundController is ...
 *
 * GameRoundController is-a ...
 * GameRoundController is ...
 */
public class GameRoundController implements Controller, GameRoundEvents
{
	// TODO: include GameRoundView member
	private GameRound gameRound;
	private boolean isHintEnabled = true;
	private boolean hasGameStartedOnce = false;
	// controller
	private GameSessionEvents controller;
	// view
	private GameRoundView<GameRoundController> gameRoundView;
	
	public GameRoundController(GameSessionEvents controller) {
		this.controller = controller;
	}
	
	public void createRound(Word word) {
		if (!hasGameStartedOnce) {
			hasGameStartedOnce = true;
		}
		else {			
			gameRoundView.reset();
			if (!isHintEnabled) {
				gameRoundView.disableHintButton();
			}
		}
		
		gameRoundView.createLetterSlots(word.getText());
		gameRound = new GameRound(word);
	}
	
	public void disableHints() {
		if (gameRoundView == null) {
			throw new IllegalStateException("Game round view has not been initialized");
		}
		isHintEnabled = false;
		gameRoundView.disableHintButton();
	}
	
	public void reset() {
		isHintEnabled = true;
		gameRoundView.reset();
	}

	@Override
	public void init()
	{
		// create view
		gameRoundView = new GameRoundView<>(this);
		// register view
		controller.onAddView(gameRoundView.getViewIdentifier(), gameRoundView);
	}
	
	@Override
	public void start()
	{
		if (gameRoundView == null) {
			throw new IllegalStateException("Game round view has not been initialized");
		}
		gameRoundView.displayView();
	}
	
	@Override
	public void onMakeGuess(char letter)
	{
		letter = Character.toLowerCase(letter);
		int triesRemainingBeforeGuess = gameRound.getTriesRemaining();
		gameRound.makeGuess(letter);
		
		// update view
		if (triesRemainingBeforeGuess > gameRound.getTriesRemaining()) { // wrong guess
			gameRoundView.setHangmanStage(gameRound.TRIES_PER_ROUND - gameRound.getTriesRemaining());
		}
		else {
			gameRoundView.revealLetter(letter);
		}
		
		// notify parent controller
		if (gameRound.isRoundOver()) {
			if (gameRound.isRoundWon()) {
				JOptionPane.showMessageDialog(
						gameRoundView,
						"You guessed it! The word was: " + gameRound.getWord().getText() + "\nRound score: " + gameRound.getScore(),
						"Round Won",
						JOptionPane.INFORMATION_MESSAGE
				);
			}
			else {
				JOptionPane.showMessageDialog(
						gameRoundView,
						"You do not have any remaining tries! The word was: " + gameRound.getWord().getText(),
						"Game Over",
						JOptionPane.INFORMATION_MESSAGE
				);
			}
			controller.onEndRound(gameRound);
		}
	}
	
	@Override
	public void onUseHint()
	{
		controller.onUseHint();
	}

	@Override
	public void onShowView(String name)
	{
		controller.onShowView(name);
	}
}
