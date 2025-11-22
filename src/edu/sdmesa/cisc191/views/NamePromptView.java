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
	private C controller;
	private JLabel promptLabel;
    private JTextField nameField;
    private JButton submitButton;
	
	/**
	 * Purpose: 
	 * @param controller
	 */
	public NamePromptView(C controller)
	{
		setController(controller);
		setupLayout();
		setupComponents();
		setupListeners();
	}

	@Override
	public void displayView()
	{
		nameField.requestFocusInWindow();
	}
	
	@Override
	public void setController(C controller)
	{
		if (controller == null) return;
		
		this.controller = controller;
	}

	private void setupLayout() {
		setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}
	
	private void setupComponents() {
		promptLabel = new JLabel("Enter your name:");
        promptLabel.setFont(new Font("Arial", Font.BOLD, 16));

        nameField = new JTextField(15);

        submitButton = new JButton("Start");

        // Layout using GridBag
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0;
        add(promptLabel, gbc);

        gbc.gridy = 1;
        add(nameField, gbc);

        gbc.gridy = 2;
        add(submitButton, gbc);
	}
	
	private void setupListeners() {
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
