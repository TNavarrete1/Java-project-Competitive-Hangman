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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Purpose: The reponsibility of WordBank is ...
 *
 * WordBank is-a ...
 * WordBank is ...
 */
public class WordBank
{
	private static final String DEFAULT_WORDS_DIR = "data/words/default";
	private static final String EXTENDED_WORDS_DIR = "data/words/extended";
	private Map<String, List<Word>> wordsByCategory = new HashMap<>();
	
	public WordBank() {
		// TODO: load word bank from txt file
		loadWordsByCategory();
	}
	
	public boolean addCategory(String category) {
		/**
		 * TODO: if category is not present then add it
		 */
		
		return true;
	}
	
	public boolean addWord(String category, Word word) {
		/**
		 * TODO: if category is not present add category
		 * might change list to set
		 * attempt to add word to set
		 */
		
		return true;
	}
	
	public boolean removeWord(String category, Word word) {
		/**
		 * TODO: remove word from category if present
		 */
		
		return true;
	}
	
	public boolean removeCategory(String category) {
		/**
		 * TODO: remove category and remove all words with it
		 * disallow removal of default categories
		 * if it is a default category remove the extended words add by user
		 * I might need two collections one for the default word bank and a
		 * second for the extended list and additional categories added by user
		 */
		
		return true;
	}
	
	public List<String> getCategories() {
		return new ArrayList<>();
	}
	
	public List<Word> getWords(String category) {
		return new ArrayList<>();
	}
	
	// ** helper functions **
	private void loadWordsByCategory() {
		File defaultDir = new File(DEFAULT_WORDS_DIR);
		File extendedDir = new File(EXTENDED_WORDS_DIR);
		List<File> files = new ArrayList<>();
		// get all available files from default and extended sections
		loadFiles(defaultDir, files);
		loadFiles(extendedDir, files);
		
		// process each file
		for (File file : files) {
			try
			{
				Scanner scanner = new Scanner(file);
				if (!scanner.hasNextLine()) continue; // file is empty go to next file
				
				String category = scanner.nextLine(); // first line is category
				wordsByCategory.putIfAbsent(category, new ArrayList<>());
				while (scanner.hasNextLine()) {
					String[] parts = scanner.nextLine().split("|");
					String word = parts[0];
					String hint = parts[1];
					wordsByCategory.get(category).add(new Word(word, hint, word.split("").length));
				}
				scanner.close();
			}
			catch (FileNotFoundException e)
			{}
		}
	}
	
	private void loadFiles(File dir, List<File> files) {
		if (!dir.exists()) return; // TODO: throw some exception
		
		if (dir.exists() && !dir.isDirectory()) { // it's a file
			files.add(dir);
			return;
		}
		
		File[] dirFiles = dir.listFiles();
		if (dirFiles.length == 0) { // empty directory
			return;
		}
		
		for (File file : dirFiles) {
			if (file.isDirectory()) { // recursively load sub-directories
				loadFiles(file, files);
			}
			else {
				files.add(file);
			}
		}
		
	}
}
