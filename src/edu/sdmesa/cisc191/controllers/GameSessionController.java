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

import edu.sdmesa.cisc191.events.GameSessionEvents;
import edu.sdmesa.cisc191.events.MainEvents;
import edu.sdmesa.cisc191.models.GameSession;
import edu.sdmesa.cisc191.models.Player;
import edu.sdmesa.cisc191.models.WordBank;
import edu.sdmesa.cisc191.views.NamePromptView;

/**
 * Purpose: The reponsibility of GameSessionController is ...
 *
 * GameSessionController is-a ...
 * GameSessionController is ...
 */
public class GameSessionController extends Controller implements GameSessionEvents
{
	private WordBank wordBank = new WordBank();
	private GameSession gameSession = new GameSession();
	private Controller gameRoundController = new GameRoundController();
	private MainEvents mainController;
	// views
	private NamePromptView<GameSessionEvents> namePromptView = new NamePromptView<>(this);
	
	
	/**
	 * Purpose: 
	 */
	public GameSessionController(MainEvents mainController)
	{
		this.mainController = mainController;
	}

	@Override
	public void init()
	{
		mainController.onAddView("nameViewPrompt", namePromptView);
		requestPlayerNameInput();
	}
	
	public void onPlayerNameSubmitted(String playerName) {
		
	}
	
	private void requestPlayerNameInput() {
		mainController.onShowView("nameViewPrompt");
		namePromptView.displayView();
	}

	@Override
	public void onPlayerNameEntered(String playerName)
	{
		System.out.println("Player name: " + playerName);
		gameSession.setPlayer(new Player(playerName));
	}
}
