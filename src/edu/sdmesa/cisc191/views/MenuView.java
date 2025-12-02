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
* Version: 2025-11-23
*/
package edu.sdmesa.cisc191.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.sdmesa.cisc191.events.MainEvents;

/**
 * Purpose: The reponsibility of MenuView is ...
 *
 * MenuView is-a ...
 * MenuView is ...
 */
public class MenuView<C extends MainEvents> extends JPanel implements View<C>
{
	/**
	 * MenuView.java has-a/has-many serialVersionUID
	 */
	private static final long serialVersionUID = 6267388478203683670L;
	private final String ID = "menuView";
	private C controller;
	
	public MenuView(C controller) {
		setController(controller);
		setupComponents();
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

	@Override
	public String getViewIdentifier()
	{
		return ID;
	}
	
	private void setupComponents() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
		// header
		JLabel header = new JLabel("Competitive Hangman", SwingConstants.CENTER);
		header.setBorder(BorderFactory.createEmptyBorder(40,20,30,20));
		header.setFont(new Font("Arial", Font.BOLD, 28));
		add(header, BorderLayout.NORTH);
		
		// button list
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		
		// create buttons
		JButton playGameButton = new JButton("Play Game");
		JButton scoreBoardButton = new JButton("Score Board");
		JButton addWordsButton = new JButton("Add New Words");
		JButton[] buttons = {playGameButton, scoreBoardButton, addWordsButton};
		
		// add listeners to buttons
		playGameButton.addActionListener((ActionEvent e) -> controller.onStartNewGame());
		scoreBoardButton.addActionListener((ActionEvent e) -> controller.onGoToScoreBoard());
		addWordsButton.addActionListener((ActionEvent e) -> controller.onGoToAddWords());
		
		// set button styles and add to button panel
		for (JButton button : buttons) {
			button.setAlignmentX(Component.CENTER_ALIGNMENT);
			button.setMaximumSize(new Dimension(200, 40));
			button.setFocusable(false);
			buttonPanel.add(button);
			buttonPanel.add(Box.createRigidArea(new Dimension(0,15))); // gap spacing
		}
		
		add(buttonPanel, BorderLayout.CENTER);
	}

}
