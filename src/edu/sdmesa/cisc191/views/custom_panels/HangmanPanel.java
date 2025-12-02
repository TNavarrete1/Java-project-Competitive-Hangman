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
* Version: 2025-12-01
*/
package edu.sdmesa.cisc191.views.custom_panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import edu.sdmesa.cisc191.models.GameRound;

/**
 * Purpose: The reponsibility of HangmanPanel is ...
 *
 * HangmanPanel is-a ...
 * HangmanPanel is ...
 */
public class HangmanPanel extends JPanel
{

	/**
	 * HangmanPanel.java has-a/has-many serialVersionUID
	 */
	private static final long serialVersionUID = -903982277170682932L;
	private int stage = 0; // 7 stages
	private double scale;
	private int offsetX;
	private int offsetY;
	
	public void setStage(int stage) {
		if (stage < 0 || stage > GameRound.TRIES_PER_ROUND) {
			throw new IllegalArgumentException("Hangman stage must be within range 0-6");
		}
		
		this.stage = stage;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.setColor(Color.black);
		
		int width = getWidth();
		int height = getHeight();
		
		// virtual design size
		final int DESIGN_WIDTH = 400;
		final int DESIGN_HEIGHT = 400;
		
		// scaling used to increase virtual design to height and width scale
		double scaleX = width / (double) DESIGN_WIDTH;
		double scaleY = height / (double) DESIGN_HEIGHT;
		scale = Math.min(scaleX, scaleY); // used to prevent stretching
		
		// stroke
		float stroke = (float) (10 * scale);
		graphics2D.setStroke(new BasicStroke(stroke));
		int strokeOffset = (int) (stroke / 2.0);
		
		// centering offset
		int drawWidth = (int) (DESIGN_WIDTH * scale);
		int drawHeight = (int) (DESIGN_HEIGHT * scale);
		// used to center horizontally if width is larger than design width
		offsetX = (width - drawWidth) / 2;
		// used to center vertically if height is larger than design height
		offsetY = (height - drawHeight) / 2;
		
		// design measurements
		// base
		int baseX1 = 0;
		int baseX2 = 250;
		int baseY = 400;
		// vertical pole
		int verticalPoleX = 100;
		int verticalPoleY1 = 400;
		int verticalPoleY2 = 0;
		// top pole
		int topPoleY = 0;
		int topPoleX1 = 100;
		int topPoleX2 = 350;
		// corner pole
		int cornerPoleX1 = 100;
		int cornerPoleX2 = 150;
		int cornerPoleY1 = 50;
		int cornerPoleY2 = 0;
		// rope
		int ropeX = 350;
		int ropeY1 = 0;
		int ropeY2 = 75;
		// stages
		// head
		int headX = 350;
		int headY = 75;
		int headWidth = 75;
		// body
		int bodyX = 350;
		int bodyY1 = 150;
		int bodyY2 = 250;
		// left arm
		int leftArmX1 = 350;
		int leftArmX2 = 310;
		int leftArmY1 = 155;
		int leftArmY2 = 220;
		// right arm
		int rightArmX1 = 350;
		int rightArmX2 = 395;
		int rightArmY1 = 155;
		int rightArmY2 = 220;
		// left leg
		int leftLegX1 = 350;
		int leftLegX2 = 310;
		int leftLegY1 = 250;
		int leftLegY2 = 325;
		// right leg
		int rightLegX1 = 350;
		int rightLegX2 = 380;
		int rightLegY1 = 250;
		int rightLegY2 = 325;
		
		// drawing the structure
		// base
		graphics2D.drawLine(scaledX(baseX1) + strokeOffset, scaledY(baseY) - strokeOffset, scaledX(baseX2), scaledY(baseY) - strokeOffset);
		// vertical pole above base
		graphics2D.drawLine(scaledX(verticalPoleX), scaledY(verticalPoleY1), scaledX(verticalPoleX), scaledY(verticalPoleY2));
		// top horizontal pole
		graphics2D.drawLine(scaledX(topPoleX1), scaledY(topPoleY) + strokeOffset, scaledX(topPoleX2), scaledY(topPoleY) + strokeOffset);
		// corner pole
		graphics2D.drawLine(scaledX(cornerPoleX1), scaledY(cornerPoleY1) + strokeOffset, scaledX(cornerPoleX2), scaledY(cornerPoleY2) + strokeOffset);
		// rope
		graphics2D.drawLine(scaledX(ropeX), scaledY(ropeY1), scaledX(ropeX), scaledY(ropeY2));
		
		// stages
		// head
		if (stage >= 1) {
			graphics2D.drawOval(scaledX(headX - headWidth / 2), scaledY(headY), (int)(headWidth * scale), (int)(headWidth * scale));
		}
		// body
		if (stage >= 2) {
			graphics2D.drawLine(scaledX(bodyX), scaledY(bodyY1), scaledX(bodyX), scaledY(bodyY2));
		}
		// left arm
		if (stage >= 3) {
			graphics2D.drawLine(scaledX(leftArmX1), scaledY(leftArmY1), scaledX(leftArmX2) + strokeOffset, scaledY(leftArmY2));
		}
		// right arm
		if (stage >= 4) {
			graphics2D.drawLine(scaledX(rightArmX1), scaledY(rightArmY1), scaledX(rightArmX2) - strokeOffset, scaledY(rightArmY2));
		}	
		// left leg
		if(stage >= 5) {
			graphics2D.drawLine(scaledX(leftLegX1), scaledY(leftLegY1), scaledX(leftLegX2), scaledY(leftLegY2));
		}
		// right leg
		if (stage >= 6) {
			graphics2D.drawLine(scaledX(rightLegX1), scaledY(rightLegY1), scaledX(rightLegX2), scaledY(rightLegY2));
		}
	}
	
	private int scaledX(double x) {
		return offsetX + (int) (x * scale);
	}
	
	private int scaledY(double y) {
		return offsetY + (int) (y * scale);
	}
}
