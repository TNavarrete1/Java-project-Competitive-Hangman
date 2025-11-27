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

import edu.sdmesa.cisc191.models.GameRound;
import edu.sdmesa.cisc191.models.Word;

/**
 * Purpose: The reponsibility of GameRoundController is ...
 *
 * GameRoundController is-a ...
 * GameRoundController is ...
 */
public class GameRoundController implements Controller
{
	// TODO: include GameRoundView member
	private GameRound gameRound;
	
	/**
	 * Purpose: 
	 */
	public GameRoundController(Word word)
	{
		gameRound = new GameRound(word);
	}

	@Override
	public void init()
	{
		// TODO: display view
	}
	
	@Override
	public void start()
	{
		// TODO Auto-generated method stub
		
	}
	
	public void makeGuess(Character letter) {
		gameRound.makeGuess(letter);
		if (gameRound.isRoundOver()) {
			endRound();
		}
	}
		
	public void endRound() {
		// TODO: implement logic
	}


}
