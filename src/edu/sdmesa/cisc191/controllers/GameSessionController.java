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

import edu.sdmesa.cisc191.models.GameSession;
import edu.sdmesa.cisc191.models.WordBank;

/**
 * Purpose: The reponsibility of GameSessionController is ...
 *
 * GameSessionController is-a ...
 * GameSessionController is ...
 */
public class GameSessionController extends Controller
{
	// TODO: include views
	private WordBank wordBank = new WordBank();
	private GameSession gameSession = new GameSession();
	private Controller gameRoundController = new GameRoundController();
	
	/**
	 * Purpose: 
	 */
	public GameSessionController()
	{
	}

	@Override
	public void handleUserAction(String action)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void init()
	{
		// TODO: display player name view
	}

}
