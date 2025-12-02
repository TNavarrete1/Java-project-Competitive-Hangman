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
* Version: 2025-11-26
*/
package edu.sdmesa.cisc191.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.sdmesa.cisc191.events.GameRoundEvents;
import edu.sdmesa.cisc191.views.custom_panels.HangmanPanel;

/**
 * Purpose: The reponsibility of GameRoundView is ...
 *
 * GameRoundView is-a ...
 * GameRoundView is ...
 */
public class GameRoundView<C extends GameRoundEvents> extends JPanel implements View<C>
{
	/**
	 * GameRoundView.java has-a/has-many serialVersionUID
	 */
	private static final long serialVersionUID = 3899417579194382858L;
	private static final String ID = "gameRoundView";
	private C controller;
	private JPanel slotPanel;
	private Map<Character, List<JPanel>> letterSlotsMap = new HashMap<>();
	private List<JButton> keyboardButtons = new ArrayList<>();
	private JButton hintButton;
	private HangmanPanel hangmanPanel;
	
	public GameRoundView(C controller) {
		if (controller == null) {
			throw new IllegalArgumentException("Controller cannot be null");
		}
		
		setController(controller);
		setupComponents();
		
		// check if state is correctly initialized
		if (slotPanel == null) {
			throw new IllegalStateException("Slot panel has not been initialized");
		}
		if (keyboardButtons.isEmpty()) {
			throw new IllegalStateException("Keyboard buttons have not been initialized");
		}
		if (hintButton == null) {
			throw new IllegalStateException("Hint button has not been initialized");
		}
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
	
	public void disableHintButton() {
		hintButton.setEnabled(false);
	}
	
	public void createLetterSlots(String text) {
		letterSlotsMap.clear();
		slotPanel.removeAll();
		for (char letter :  text.toCharArray()) {
			char normalizedLetter = Character.toLowerCase(letter); // used to create mapping
			
			JPanel slot = new JPanel(new BorderLayout());
			slot.setPreferredSize(new Dimension(50,50));
			
			JLabel label = new JLabel(String.valueOf(Character.toUpperCase(letter)));
			label.setFont(new Font("Arial", Font.BOLD, 20));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			
			if (normalizedLetter >= 'a' && normalizedLetter <= 'z') {
				label.setVisible(false); // empty slot
				slot.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));
				// update slots map
				letterSlotsMap.putIfAbsent(normalizedLetter, new ArrayList<>());
				letterSlotsMap.get(normalizedLetter).add(slot);
			}
			
			slot.add(label, BorderLayout.CENTER);
			slotPanel.add(slot);
		}
		
		slotPanel.revalidate();
		slotPanel.repaint();
	}
	
	public void revealLetter(char letter) {
		for (JPanel slot : letterSlotsMap.get(letter)) {
			slot.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
			slot.getComponent(0).setVisible(true);
		}
	}
	
	public void reset() {
		// letter slots
		letterSlotsMap.clear();
		slotPanel.removeAll();
		
		// keyboard
		for (JButton button : keyboardButtons) {
			button.setEnabled(true);
		}
		// hint button
		hintButton.setEnabled(true);
		
		// hangman stage
		hangmanPanel.setStage(0);
	}
	
	private void setupComponents() {
		setLayout(new BorderLayout());
		// north
		//hangman stages
		JPanel hangmanPanelWrapper = new JPanel(new GridBagLayout());
		hangmanPanel = new HangmanPanel();
		hangmanPanel.setPreferredSize(new Dimension(250,250));
		hangmanPanelWrapper.add(hangmanPanel);
		
		// center
		// letter slots
		JPanel slotPanelWrapper = new JPanel();
		slotPanelWrapper.setLayout(new BoxLayout(slotPanelWrapper, BoxLayout.Y_AXIS));
		slotPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		slotPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		slotPanelWrapper.add(Box.createVerticalGlue());
		slotPanelWrapper.add(slotPanel);
		slotPanelWrapper.add(Box.createVerticalGlue());

		
		// south
		// keyboard buttons
		JPanel keyboardGrid = new JPanel(new GridLayout(4, 7, 5, 5));
		keyboardGrid.setBackground(Color.black);
		for (int i = 0; i < 26; i++) {
			char currLetter = (char)('A' + i);
			
			JButton button = new JButton(String.valueOf(currLetter));
			button.setPreferredSize(new Dimension(60,60));
			button.setFont(new Font("Arial", Font.BOLD, 18));
			button.addActionListener((ActionEvent e) -> makeGuess(currLetter, button));
			
			keyboardButtons.add(button);
			keyboardGrid.add(button);
		}
		JPanel spacer = new JPanel();
		
		hintButton = new JButton("Hint?");
		hintButton.setPreferredSize(new Dimension(60,60));
		hintButton.setFont(new Font("Arial", Font.BOLD, 18));
		hintButton.addActionListener((ActionEvent e) -> useHint());
		
		keyboardGrid.add(spacer);
		keyboardGrid.add(hintButton);
		
		add(hangmanPanelWrapper, BorderLayout.NORTH);
		add(slotPanelWrapper, BorderLayout.CENTER);
		add(keyboardGrid, BorderLayout.SOUTH);
	}
	
	private void makeGuess(char letter, JButton button) {
		button.setEnabled(false);
		controller.onMakeGuess(Character.toLowerCase(letter));
		System.out.println(letter);
	}

	private void useHint() {
		hintButton.setEnabled(false);
		controller.onUseHint();
		System.out.println("Hint used");
	}

	/**
	 * Purpose: 
	 */
	public void setHangmanStage(int stage)
	{
		hangmanPanel.setStage(stage);
	}
}
