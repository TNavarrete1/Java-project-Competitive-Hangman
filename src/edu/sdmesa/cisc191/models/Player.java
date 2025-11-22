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
package edu.sdmesa.cisc191.models;

import java.util.Objects;

/**
 * Purpose: The reponsibility of Player is ...
 *
 * Player is-a ...
 * Player is ...
 */
public class Player implements Comparable<Player>
{
	private String name = "";
	private int score = 0;
	
	public Player() {}
	
	public Player(String name) {
		setName(name);
	}
	public Player (String name, int score) {
		setName(name);
		setScore(score);
	}
	
	// copy constructor
	public Player(Player other) {
		if (other == null) {
			return;
		}
		
		name = other.name;
		score = other.score;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if (name == null) {
			return;
		}
		this.name = name;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = Math.max(0, score);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Player other = (Player) obj;
		return name.equals(other.getName());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	
	@Override
	public int compareTo(Player other)
	{
		if (other == null) {
			return -1;
		}
		return Integer.compare(score, other.getScore()); // natural ordering
	}
}
