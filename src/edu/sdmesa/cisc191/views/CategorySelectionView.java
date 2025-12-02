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
package edu.sdmesa.cisc191.views;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import edu.sdmesa.cisc191.events.GameSessionEvents;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;

/**
 * Purpose: The reponsibility of CategorySelectionView is ...
 *
 * CategorySelectionView is-a ...
 * CategorySelectionView is ...
 */
public class CategorySelectionView<C extends GameSessionEvents> extends JPanel implements View<C>
{

	/**
	 * CategorySelectionView.java has-a/has-many serialVersionUID
	 */
	private static final long serialVersionUID = -6483222647926430102L;
	private static final String ID = "categorySelectionView";
	private C controller;
	private List<String> categories;
	JPanel grid;
	
	public CategorySelectionView(C controller, List<String> categories) {
		List<String> test = new ArrayList<>();
		test.add("Movies");
		test.add("Shows");
		test.add("Celebrities");
		test.add("Marvel");
		test.add("Breakfast Food");
		test.add("Video Games");
		test.add("Music");
		test.add("Fruits");
		test.add("Clothing Brands");
		test.add("Car Brands");
		test.add("U.S. States");
		test.add("Books");
		test.add("hhhhhhhhhhhhhhh hhhhhhhhh hhhhhhhhhhhhhhhhhh ahaaaaaaa");
		this.categories = test;
		
		setController(controller);
		setupComponents();
	}
	
	public void setCategories(List<String> categories) {
		grid.removeAll();
		this.categories = categories;
		
		// add in new list of categories into grid
		for (String category : categories) {
			String html = "<html><div style='text-align:center;'>" + category + "</div></html>";
			JButton button = new JButton(html);
			button.setPreferredSize(new Dimension(200, 75));
			button.addActionListener((ActionEvent e) -> controller.onCategorySelected(category));
			button.setVerticalAlignment(SwingConstants.CENTER);
			button.setFont(new Font("Arial", Font.BOLD, 16));
			
			grid.add(button);
		}
	}

	@Override
	public String getViewIdentifier() {
		return ID;
	}
	
	@Override
	public void displayView()
	{
		if (!isVisible()) {
			controller.onShowView(ID);
		}
		grid.revalidate(); // recomputes layout
		grid.repaint(); // repaints visual changes
	}

	@Override
	public void setController(C controller)
	{
		this.controller = controller;
	}
	
	private void setupComponents() {
		setLayout(new BorderLayout());
		
		// north
		// title label
		JLabel label = new JLabel("Choose a category", SwingConstants.CENTER);
		label.setBorder(BorderFactory.createEmptyBorder(40,20,30,20));
		label.setFont(new Font("Arial", Font.BOLD, 22));
		add(label, BorderLayout.NORTH);
		// center
		int columns = 3;
		// grid component
		grid = new JPanel(new GridLayout(0, columns, 10, 10));
		
		for (String category : categories) {
			String html = "<html><div style='text-align:center;'>" + category + "</div></html>";
			JButton button = new JButton(html);
			button.setPreferredSize(new Dimension(200, 75));
			button.addActionListener((ActionEvent e) -> controller.onCategorySelected(category));
			button.setVerticalAlignment(SwingConstants.CENTER);
			button.setFont(new Font("Arial", Font.BOLD, 16));
			
			grid.add(button);
		}
		// wrapper for grid padding
		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
		wrapper.add(grid);
		
		// scroll component
		JScrollPane scroll = new JScrollPane(wrapper);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		add(scroll, BorderLayout.CENTER);
	}
}
