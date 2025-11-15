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

/**
 * Purpose: The reponsibility of Controller is ...
 *
 * Controller is-a ...
 * Controller is ...
 */
public abstract class Controller
{
	// TODO: include view member

	/**
	 * Purpose: 
	 */
	public Controller()
	{
	}
	
	public abstract void handleUserAction(String action);
	public abstract void init();

}
