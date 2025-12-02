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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
	private JLabel categoryLabel;
	private JLabel scoreLabel;
	private JPanel header;
	
	public GameSessionView(C controller) {
		if (controller == null) {
			throw new IllegalArgumentException("Controller can not be null");
		}
		setController(controller);
		setupComponents();
		
		// make sure shared components were created
		if (layout == null) {
			throw new IllegalStateException("Layout has not been initialized");
		}
		if (container == null) {
			throw new IllegalStateException("Container has not been initialized");
		}
		if (categoryLabel == null) {
			throw new IllegalStateException("Category label has not been initialized");
		}
		if (scoreLabel == null) {
			throw new IllegalStateException("Score label has not been initialized");
		}
		if (header == null) {
			throw new IllegalStateException("Header panel has not been initialized");
		}
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
		setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
		
		// header
		header = new JPanel(new BorderLayout());
		header.setVisible(false);
		header.setBorder(BorderFactory.createEmptyBorder(0,20,30,20));
		categoryLabel = new JLabel("Category");
		categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		categoryLabel.setFont(new Font("Arial", Font.BOLD, 28));
		scoreLabel = new JLabel("Score: 0");
		scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
		header.add(categoryLabel, BorderLayout.CENTER);
		header.add(scoreLabel, BorderLayout.NORTH);
		
		// card layout 
		layout = new CardLayout();
		// container for cards
		container = new JPanel(layout);
		
		// footer
		JPanel footer = new JPanel();
		footer.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
		
		
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
		
		add(header, BorderLayout.NORTH);
		add(container, BorderLayout.CENTER);
		add(footer, BorderLayout.SOUTH);
	}
	
	public void addView(String name, JPanel view) {
		if (view == null) return;
		container.add(view, name);
	}
	
	public void showView(String name) {
		layout.show(container, name);
	}

	public void setCategory(String category) {
		if (category == null || category.isEmpty()) {
			throw new IllegalArgumentException("Category can not be null or empty");
		}
		categoryLabel.setText(category);
		revalidate();
		repaint();
	}
	
	public void setScore(int score) {
		scoreLabel.setText("Score: " + String.valueOf(score));
		revalidate();
		repaint();
	}
	
	public void showHeader() {
		header.setVisible(true);
		revalidate();
		repaint();
	}
	
	public void reset() {
		categoryLabel.setText("Category");
		scoreLabel.setText("Score: 0");
		header.setVisible(false);
		revalidate();
		repaint();
	}
}
