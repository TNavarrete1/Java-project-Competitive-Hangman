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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Purpose: The reponsibility of Scorebboard is ...
 *
 * ScoreBoard is-a ...
 * ScoreBoard is ...
 */
public class ScoreBoard
{
	private List<Map.Entry<Player, Integer>> scores = new ArrayList<>();
	
	ScoreBoard() {
		// TODO: load score board from data
	}
	
	public List<Map.Entry<Player, Integer>> getScores() {
		return scores;
	}
	
	public boolean addScore(Player player, int score) {
		/** 
		 * TODO: check if player is in score board 
		 * if they are then remove old score 
		 * insert new score
		 * thinking of using max heap now to keep a sorted collection
		 */
		
		return true;
	}
}
