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
* Version: 2025-11-22
*/
package edu.sdmesa.cisc191.views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import edu.sdmesa.cisc191.events.GameSessionEvents;

/**
 * Purpose: The reponsibility of GameSessionView is ...
 *
 * GameSessionView is-a ...
 * GameSessionView is ...
 */
public class GameSessionView<C extends GameSessionEvents> extends JPanel implements View<C>
{
	/**
	 * GameSessionView.java has-a/has-many serialVersionUID
	 */
	private static final long serialVersionUID = 5285718914117661832L;
	private static final String ID = "gameSessionView";
	private C controller;
	private CardLayout layout;
	private JPanel container;
	private JPanel footer;
	
	public GameSessionView(C controller) {
		setController(controller);
		setupComponents();
	}
	
	@Override
	public String getViewIdentifier() {
		return ID;
	}

	@Override
	public void displayView()
	{
		controller.onShowView(ID);
	}

	@Override
	public void setController(C controller)
	{
		this.controller = controller;
	}
	
	private void setupComponents() {
		// wrapper container layout
		setLayout(new BorderLayout());
		BorderFactory.createEmptyBorder(20,0,20,0);
		
		// card layout 
		layout = new CardLayout();
		// container for cards
		container = new JPanel(layout);
		
		// footer
		footer = new JPanel();
		
		add(container, BorderLayout.CENTER);
		add(footer, BorderLayout.SOUTH);
		
		JButton newGameButton = new JButton("New Game");
		JButton menuButton = new JButton("Menu");
		// styles
		newGameButton.setFont(new Font("Arial", Font.BOLD, 16));
		menuButton.setFont(new Font("Arial", Font.BOLD, 16));
		// listeners
		newGameButton.addActionListener((ActionEvent e) -> controller.onStartNewGame());
		menuButton.addActionListener((ActionEvent e) -> controller.onGoToMenu());
		
		footer.add(newGameButton);
		footer.add(menuButton);
	}
	
	public void addView(String name, JPanel view) {
		if (view == null) return;
		container.add(view, name);
	}
	
	public void showView(String name) {
		layout.show(container, name);
	}

}
