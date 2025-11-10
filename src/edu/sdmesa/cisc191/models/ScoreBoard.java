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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Purpose: The reponsibility of Scorebboard is ...
 *
 * ScoreBoard is-a ...
 * ScoreBoard is ...
 */
public class ScoreBoard
{
	private static final String SCORE_BOARD_FILE_PATH = "score_board/score_board.txt";
	private static final int MAX_PLAYER_COUNT = 20; // makes sure only 20 players are on score board
	private PriorityQueue<Player> playerMinHeap = new PriorityQueue<>();
	private Map<String, Player> playerMap = new HashMap<>(); // used to check for players in O(1)
	
	ScoreBoard() {
		// load score board from data
		loadScoreBoard("data/" + SCORE_BOARD_FILE_PATH);
	}
	
	ScoreBoard(File sourceDirectory) {
		loadScoreBoard(sourceDirectory.getAbsolutePath() + SCORE_BOARD_FILE_PATH);
	}
	
	public List<Player> getPlayers() {
		List<Player> players = new ArrayList<>(playerMinHeap);
		Collections.sort(players);
		
		return List.copyOf(players);
	}
	
	public boolean addPlayer(Player player) {
		/** 
		 * TODO: check if player is in score board 
		 * if they are then remove old score 
		 * insert new score
		 * thinking of using min heap now to keep a sorted collection
		 * min heap makes it easy to add player and remove from the lowest
		 * score from the top
		 */
		if (playerMap.containsKey(player.getName())) {
			Player foundPlayer = playerMap.get(player.getName());
			if (player.getScore() < foundPlayer.getScore()) return false;
			
			// update player score
			playerMinHeap.remove(foundPlayer);
			foundPlayer.setScore(player.getScore()); // updates player score
			playerMinHeap.add(foundPlayer); // adds same player in with new score
		}
		else { // new player
			playerMinHeap.add(player);
			playerMap.put(player.getName(), player);
			if (playerMinHeap.size() > MAX_PLAYER_COUNT) { // removes player at bottom
				Player bottomPlayer = playerMinHeap.poll();
				playerMap.remove(bottomPlayer.getName());
				
				if (bottomPlayer.equals(player)) return false; // player didn't make the list
			}
		}
		
		return true;
	}
	
	public boolean save() {
		try (PrintWriter out = new PrintWriter(new FileWriter(SCORE_BOARD_FILE_PATH))) {
			List<Player> players = new ArrayList<>(playerMinHeap);
			Collections.sort(players, Comparator.reverseOrder());
			for (Player player : players) {
				out.println(player.getName() + "|" + player.getScore());
			}
		}
		catch (IOException e) {
			return false;
		}
		return true;
	}
	
	private void loadScoreBoard(String filePath) {
		// get file
		File file = getFile(filePath);
		
		// load all scores from file
		loadScoresFromFile(file);
	}
	
	private File getFile(String filePath) {
		if (filePath == null) return new File("");
		
		return new File(filePath);
	}
	
	private void loadScoresFromFile(File file) {
		try (Scanner scanner = new Scanner(file))
		{
			if (!scanner.hasNextLine()) return; // file is empty
			
			int playerCount = 0;
			while (scanner.hasNextLine() && playerCount < MAX_PLAYER_COUNT) {
				String[] parts = scanner.nextLine().split("\\|");
				String playerName = parts[0];
				int score = Integer.parseInt(parts[1]);
				Player player = new Player(playerName, score);
				playerMinHeap.add(player);
				playerMap.put(playerName, player);
				playerCount++;
			}
		}
		catch (FileNotFoundException e) 
		{}
	}
}
