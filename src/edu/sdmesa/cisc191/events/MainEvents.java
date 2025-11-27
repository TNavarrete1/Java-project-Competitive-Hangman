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
* Version: 2025-11-21
*/
package edu.sdmesa.cisc191.events;

import javax.swing.JPanel;

/**
 * Purpose: The reponsibility of MainEvents is ...
 *
 * MainEvents is-a ...
 * MainEvents is ...
 */
public interface MainEvents extends GameEvents
{
	public void onAddView(String name, JPanel view);
	public void onShowView(String name);
	public void onStartNewGame();
	public void onGoToScoreBoard();
	public void onGoToAddWords();
	public void onGoToMenu();
}
