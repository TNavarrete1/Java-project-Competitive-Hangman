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
* Version: 2025-10-31
*/
package edu.sdmesa.cisc191;

import java.util.List;
import java.util.Scanner;
import java.io.File;

import edu.sdmesa.cisc191.controllers.Controller;
import edu.sdmesa.cisc191.controllers.GameSessionController;
import edu.sdmesa.cisc191.controllers.MainController;
import edu.sdmesa.cisc191.models.Word;
import edu.sdmesa.cisc191.models.WordBank;

/**
 * Purpose: The reponsibility of main is ...
 *
 * main is-a ...
 * main is ...
 */
public class Main
{

	/**
	 * Purpose: 
	 * @param args
	 */
	public static void main(String[] args)
	{
		Controller controller = new MainController();
		controller.init();
		controller.start();
	}

}
