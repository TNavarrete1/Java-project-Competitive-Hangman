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
package edu.sdmesa.cisc191.controllers;

import javax.swing.JPanel;

import edu.sdmesa.cisc191.events.MainEvents;

/**
 * Purpose: The reponsibility of MainController is ...
 *
 * MainController is-a ...
 * MainController is ...
 */
public class MainController extends Controller implements MainEvents
{
	Controller gameSessionController = new GameSessionController(this);
	// views
	MainView<MainController> mainView = new MainView<>(this);
	
	public MainController() {}

	@Override
	public void init()
	{
		mainView.displayView();
		gameSessionController.init();
	}

	@Override
	public void onAddView(String name, JPanel view)
	{
		mainView.addView(name, view);
	}

	@Override
	public void onShowView(String name)
	{
		mainView.showView(name);
	}

}
