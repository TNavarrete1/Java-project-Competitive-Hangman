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

import javax.swing.JFrame;

import edu.sdmesa.cisc191.events.GameSessionEvents;

import java.util.List;

/**
 * Purpose: The reponsibility of CategorySelectionView is ...
 *
 * CategorySelectionView is-a ...
 * CategorySelectionView is ...
 */
public class CategorySelectionView<C extends GameSessionEvents> extends JFrame implements View<C>
{

	/**
	 * CategorySelectionView.java has-a/has-many serialVersionUID
	 */
	private static final long serialVersionUID = -6483222647926430102L;
	private C controller;
	private List<String> categories;
	
	public CategorySelectionView(C controller, List<String> categories) {
		setController(controller);
		
	}

	@Override
	public void displayView()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setController(C controller)
	{
		if (controller == null) return;
		this.controller = controller;
	}
	
	private void setupLayout() {
		
	}
	
	private void setupComponents() {
		
	}
	
	private void setupListeners() {
		
	}

}
