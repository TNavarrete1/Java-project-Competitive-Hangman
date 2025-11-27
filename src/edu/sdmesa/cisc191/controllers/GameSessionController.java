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

import javax.swing.JPanel;

import edu.sdmesa.cisc191.events.GameSessionEvents;
import edu.sdmesa.cisc191.events.MainEvents;
import edu.sdmesa.cisc191.models.GameSession;
import edu.sdmesa.cisc191.models.Player;
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
	private boolean hasGameStartedOnce = false;
	private Player player;
	// controllers
	private Controller gameRoundController;
	private MainEvents mainController;
	// views 
	private GameSessionView<GameSessionEvents> gameSessionView;
	private NamePromptView<GameSessionEvents> namePromptView;
	private CategorySelectionView<GameSessionEvents> categorySelectionView;
	
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
		gameSession = new GameSession(player, category, wordBank.getAllWords(category));
		
		// show game round
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
	
	private void requestPlayerNameInput() {
		namePromptView.displayView();
	}
	
	private void startNewGame() {
		if (!hasGameStartedOnce) {
			hasGameStartedOnce = true;
		}
		else {
			reset();
		}
		// show game session view
		gameSessionView.displayView();
		// show category card
		requestPlayerNameInput();
	}
	
	private void reset() {
		// retrieves all categories again in case wordBank state changed
		categorySelectionView.setCategories(wordBank.getAllCategories());
		// reset views that need reset
		namePromptView.reset();
	}
}
