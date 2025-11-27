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
* Version: 2025-11-15
*/
package edu.sdmesa.cisc191.views;

import edu.sdmesa.cisc191.events.GameEvents;

/**
 * Purpose: The reponsibility of View is ...
 *
 * View is-a ...
 * View is ...
 */
public interface View<C extends GameEvents>
{
	public void displayView();
	public void setController(C controller);
	public String getViewIdentifier();
}
