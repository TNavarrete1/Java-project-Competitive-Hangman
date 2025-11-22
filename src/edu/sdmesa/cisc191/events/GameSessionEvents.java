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
* Version: 2025-11-19
*/
package edu.sdmesa.cisc191.events;

/**
 * Purpose: The reponsibility of GameSessionEvents is ...
 *
 * GameSessionEvents is-a ...
 * GameSessionEvents is ...
 */
public interface GameSessionEvents extends GameEvents
{
	public void onPlayerNameEntered(String playerName);
}
