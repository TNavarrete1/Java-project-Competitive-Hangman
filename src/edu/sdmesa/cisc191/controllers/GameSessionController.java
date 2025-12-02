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

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.sdmesa.cisc191.events.GameSessionEvents;
import edu.sdmesa.cisc191.events.MainEvents;
import edu.sdmesa.cisc191.models.GameRound;
import edu.sdmesa.cisc191.models.GameSession;
import edu.sdmesa.cisc191.models.Player;
import edu.sdmesa.cisc191.models.Word;
import edu.sdmesa.cisc191.models.WordBank;
import edu.sdmesa.cisc191.views.CategorySelectionView;
import edu.sdmesa.cisc191.views.GameSessionView;
import edu.sdmesa.cisc191.views.NamePromptView;

/**
 * Purpose: The reponsibility of GameSessionController is ...
 *
 * GameSessionController is-a ...
 * GameSessionController is ...
 */
public class GameSessionController implements Controller, GameSessionEvents
{
	private WordBank wordBank;
	private GameSession gameSession;
	private Player player;
	private boolean hasGameStartedOnce = false;
	// controllers
	private GameRoundController gameRoundController = new GameRoundController(this);
	private MainEvents mainController;
	// views 
	private GameSessionView<GameSessionController> gameSessionView;
	private NamePromptView<GameSessionController> namePromptView;
	private CategorySelectionView<GameSessionController> categorySelectionView;
	
	/**
	 * Purpose: 
	 */
	public GameSessionController(MainEvents mainController, WordBank wordBank)
	{
		this.mainController = mainController;
		this.wordBank = wordBank;
	}

	@Override
	public void init()
	{
		// create views
		gameSessionView = new GameSessionView<>(this);
		namePromptView = new NamePromptView<>(this);
		categorySelectionView = new CategorySelectionView<>(this, wordBank.getAllCategories());
		// register views
		gameSessionView.addView(namePromptView.getViewIdentifier(), namePromptView);
		gameSessionView.addView(categorySelectionView.getViewIdentifier(), categorySelectionView);
		mainController.onAddView(gameSessionView.getViewIdentifier(), gameSessionView);	
		gameRoundController.init();
	}
	
	@Override
	public void start() {
		startNewGame();
	}

	@Override
	public void onPlayerNameEntered(String playerName)
	{
		System.out.println("Player name: " + playerName);
		player = new Player(playerName);
		categorySelectionView.displayView();
	}

	@Override
	public void onCategorySelected(String category)
	{
		System.out.println("Category: " + category);
		// create game session
		gameSession = new GameSession(player, category, wordBank.getAllWords(category));
		
		// update view
		gameSessionView.setCategory(category);
		gameSessionView.setScore(0);
		// display header
		gameSessionView.showHeader();
		
		// TODO: get word from game session
		Word word = gameSession.getNextWord();
		// TODO: create new round with word
		gameRoundController.createRound(word);
		// TODO: start round
		gameRoundController.start();
	}

	@Override
	public void onStartNewGame()
	{
		startNewGame();
	}

	@Override
	public void onGoToMenu()
	{
		System.out.println("Main menu");
		mainController.onGoToMenu();
	}
	
	@Override
	public void onAddView(String name, JPanel view)
	{
		gameSessionView.addView(name, view);
	}

	@Override
	public void onShowView(String name)
	{
		if (name == gameSessionView.getViewIdentifier()) {
			mainController.onShowView(name);
		}
		else {
			gameSessionView.showView(name);
		}
	}
	
	@Override
	public void onUseHint()
	{
		gameSession.useHint();
		if (gameSession.getHintsRemaining() == 0) {
			gameRoundController.disableHints();
		}
	}
	
	@Override
	public void onEndRound(GameRound gameRound) {
		// updates score and adds round to session round history
		gameSession.addRound(gameRound);
		player.setScore(gameSession.getScore());
		
		// update game session header
		gameSessionView.setScore(gameSession.getScore());
		
		if (gameRound.isRoundWon() && !gameSession.hasMoreWords()) {
			System.out.println("No more words");
			// show message to player
			JOptionPane.showMessageDialog(
					gameSessionView,
					"Congratulations! There are no more words left to guess\nSession score: " + player.getScore(),
					"You Win",
					JOptionPane.INFORMATION_MESSAGE
			);
			
			// notify parent controller
			mainController.onEndGameSession(player);
			return;
		}
		else if (!gameRound.isRoundWon()) {
			JOptionPane.showMessageDialog(
					gameSessionView,
					"Better luck next time!\nSession score: " + player.getScore(),
					"Game Over",
					JOptionPane.INFORMATION_MESSAGE
			);
			mainController.onEndGameSession(player);
			return;
		}
		
		Word word = gameSession.getNextWord();
		gameRoundController.createRound(word);
	}
	
	private void startNewGame() {
		if (!hasGameStartedOnce) {
			hasGameStartedOnce = true;
		}
		else { // reset everything
			reset();
			gameRoundController.reset();
		}
		// show game session view
		gameSessionView.displayView();
		// show name card
		namePromptView.displayView();
	}
	
	private void reset() {
		// retrieves all categories again in case wordBank state changed
		categorySelectionView.setCategories(wordBank.getAllCategories());
		// reset views that need reset
		namePromptView.reset();
		gameSessionView.reset();
	}
}
