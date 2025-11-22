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

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.sdmesa.cisc191.events.MainEvents;
import edu.sdmesa.cisc191.views.View;

/**
 * Purpose: The reponsibility of MainView is ...
 *
 * MainView is-a ...
 * MainView is ...
 */
public class MainView<C extends MainEvents> extends JFrame implements View<C>
{
	private C controller;
	private CardLayout layout = new CardLayout();
	private JPanel container = new JPanel(layout);
	
	public MainView(C controller) {
		setController(controller);
		setupLayout();
	}

	@Override
	public void setController(C controller)
	{
		if (controller == null) return;
		this.controller = controller;
	}

	@Override
	public void displayView()
	{
		setVisible(true);
	}
	
	public void addView(String name, JPanel view) {
		if (view == null) return;
		container.add(view, name);
	}
	
	public void showView(String name) {
		layout.show(container, name);
	}
	
	private void setupLayout() {
		setTitle("Competitive Hangman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setContentPane(container);
	}
}
