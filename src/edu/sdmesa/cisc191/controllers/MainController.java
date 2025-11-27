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
import edu.sdmesa.cisc191.views.MainView;
import edu.sdmesa.cisc191.views.MenuView;

import edu.sdmesa.cisc191.models.WordBank;

/**
 * Purpose: The reponsibility of MainController is ...
 *
 * MainController is-a ...
 * MainController is ...
 */
public class MainController implements Controller, MainEvents
{
	WordBank wordBank = new WordBank();
	// view
	MainView<MainEvents> mainView;
	MenuView<MainEvents> menuView;
	// controller
	Controller gameSessionController = new GameSessionController(this, wordBank);
	
	public MainController() {}

	@Override
	public void init()
	{
		// create views
		mainView = new MainView<>(); // root view
		menuView = new MenuView<>(this);
		//register views
		mainView.addView(menuView.getViewIdentifier(), menuView);
		gameSessionController.init();
	}
	
	@Override
	public void start() {
		menuView.displayView();
		mainView.displayView();
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

	@Override
	public void onStartNewGame()
	{
		gameSessionController.start();
	}

	@Override
	public void onGoToScoreBoard()
	{
		System.out.println("ScoreBoard");
	}

	@Override
	public void onGoToAddWords()
	{
		System.out.println("AddWords");
	}

	@Override
	public void onGoToMenu()
	{
		menuView.displayView();
	}

}
