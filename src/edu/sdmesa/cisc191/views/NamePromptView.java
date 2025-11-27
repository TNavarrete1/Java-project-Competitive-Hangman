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

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import edu.sdmesa.cisc191.events.GameSessionEvents;

/**
 * Purpose: The responsibility of NamePromptView is ...
 *
 * NamePromptView is-a ...
 * NamePromptView is ...
 */
public class NamePromptView<C extends GameSessionEvents> extends JPanel implements View<C>
{
	/**
	 * NamePromptView.java has-a/has-many serialVersionUID
	 */
	private static final long serialVersionUID = -3702945622326777104L;
	private static final String ID = "namePromptView";
	private C controller;
	JTextField nameField;
	
	/**
	 * Purpose: 
	 * @param controller
	 */
	public NamePromptView(C controller)
	{
		setController(controller);
		setupComponents();
	}
	
	public void reset() {
		nameField.setText("");
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
		revalidate(); // recomputes layout
		repaint(); // repaints visual changes
		
		SwingUtilities.invokeLater(() -> nameField.requestFocusInWindow());
	}
	
	@Override
	public void setController(C controller)
	{		
		this.controller = controller;
	}
	
	private void setupComponents() {
		setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        
		JLabel promptLabel = new JLabel("Enter your name:");
        promptLabel.setFont(new Font("Arial", Font.BOLD, 16));

        nameField = new JTextField(15);

        JButton submitButton = new JButton("Start");

        // Layout using GridBag
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0;
        add(promptLabel, gbc);

        gbc.gridy = 1;
        add(nameField, gbc);

        gbc.gridy = 2;
        add(submitButton, gbc);
        
        // listeners
		submitButton.addActionListener((ActionEvent e) -> submitName());
        // allow Enter key to submit
        nameField.addActionListener((ActionEvent e) -> submitName());
	}
	
	private void submitName() {
		String name = nameField.getText().trim();
        if (!name.isEmpty()) {
            controller.onPlayerNameEntered(name);
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a name.",
                    "Missing Name",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}
