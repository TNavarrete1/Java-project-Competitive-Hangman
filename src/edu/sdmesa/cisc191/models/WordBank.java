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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Purpose: The reponsibility of WordBank is ...
 *
 * WordBank is-a ...
 * WordBank is ...
 */
public class WordBank
{
	private static final String DEFAULT_WORDS_DIR = "/words/default";
	private static final String EXTENDED_WORDS_DIR = "/words/extended";
	private Map<String, Set<Word>> defaultWordsByCategory = new HashMap<>();
	private Map<String, Set<Word>> extendedWordsByCategory = new HashMap<>();
	
	public WordBank() {
		File dir = new File("data");
		loadAllWords(dir);
	}
	
	public WordBank(File sourceDirectory) {
		loadAllWords(sourceDirectory);
	}
	
	public boolean addCategory(String category) {
		if (extendedWordsByCategory.containsKey(category)) return false;
		
		extendedWordsByCategory.put(category, new HashSet<>());
		
		return true;
	}
	
	public boolean addWord(String category, Word word) {
		extendedWordsByCategory.putIfAbsent(category, new HashSet<>());
		
		return extendedWordsByCategory.get(category).add(word);
	}
	
	public boolean removeWord(String category, Word word) {
		if (!extendedWordsByCategory.containsKey(category)) return false;
		
		return extendedWordsByCategory.get(category).remove(word);
	}
	
	public boolean removeCategory(String category) {
		if (!extendedWordsByCategory.containsKey(category)) return false;
		
		extendedWordsByCategory.remove(category);
		
		return true;
	}
	
	public List<String> getAllCategories() {
		Set<String> categories = new HashSet<>();
		categories.addAll(defaultWordsByCategory.keySet());
		categories.addAll(extendedWordsByCategory.keySet());
		
		return new ArrayList<>(categories);
	}
	
	public List<String> getDefaultCategories() {
		return new ArrayList<>(defaultWordsByCategory.keySet());
	}
	
	public List<String> getExtendedCategories() {
		return new ArrayList<>(extendedWordsByCategory.keySet());
	}
	
	public List<Word> getAllWords(String category) {
		Set<Word> words = new HashSet<>();
		for (Word word : defaultWordsByCategory.getOrDefault(category, new HashSet<>())) {
			words.add(new Word(word));
		}
		for (Word word : extendedWordsByCategory.getOrDefault(category, new HashSet<>())) {
			words.add(new Word(word));
		}
		
		return new ArrayList<>(words);
	}
	
	public List<Word> getDefaultWords(String category) {
		List<Word> words = new ArrayList<>();
		for (Word word : defaultWordsByCategory.getOrDefault(category, new HashSet<>())) {
			words.add(new Word(word));
		}
		
		return words;
	}
	
	public List<Word> getExtendedWords(String category) {
		List<Word> words = new ArrayList<>();
		for (Word word : extendedWordsByCategory.getOrDefault(category, new HashSet<>())) {
			words.add(new Word(word));
		}
		
		return words;
	}
	
	public boolean saveAllWords() {
		File dir = new File(EXTENDED_WORDS_DIR);
		List<File> files = getFiles(dir);
		// delete old files
		for (File file : files) {
			file.delete();
		}
		
		List<String> extendedCategories = getExtendedCategories();
		// create new files
		for (String category : extendedCategories) {
			String path = EXTENDED_WORDS_DIR + "/" + category.toLowerCase().replace(" ", "_") + ".txt";
			try (PrintWriter out = new PrintWriter(new FileWriter(path)))
			{
				
				out.println(category); // first line is category name
				for (Word word : extendedWordsByCategory.getOrDefault(category, new HashSet<>())) {
					out.println(word.getText() + "|" + word.getHint()); // word entries
				}
			}
			catch (IOException e)
			{
				return false;
			}
		}
		return true;
	}
	
	// ** helper functions **
	private void loadAllWords(File sourceDirectory) {
		// load word bank from txt files
		if (defaultWordsByCategory.isEmpty()) {
			File defaultDir = new File(sourceDirectory + DEFAULT_WORDS_DIR);
			try {
				
				loadWordsByCategory(defaultDir, defaultWordsByCategory);
			}
			catch (FileNotFoundException e) {
				
			}
		}
		if (extendedWordsByCategory.isEmpty()) {	
			File extendedDir = new File(sourceDirectory + EXTENDED_WORDS_DIR);
			try {
				loadWordsByCategory(extendedDir, extendedWordsByCategory);
			}
			catch (FileNotFoundException e) {
				
			}
		}
	}
	
	private void loadWordsByCategory(File dir, Map<String, Set<Word>> targetMap) throws FileNotFoundException {

		// get all available files from directory
		List<File> files = getFiles(dir);
		
		// load all words from files
		loadWordsFromFiles(files, targetMap);
	}
	
	private List<File> getFiles(File dir) {
		List<File> files = new ArrayList<>();
		if (!dir.exists()) return files; // TODO: throw some exception
		
		if (!dir.isDirectory()) { // it's a file
			files.add(dir);
			return files;
		}
		
		File[] dirFiles = dir.listFiles();
		if (dirFiles.length == 0) { // empty directory
			return files;
		}
		
		for (File file : dirFiles) {
			files.add(file);
		}
		
		return files;
	}
	
	private void loadWordsFromFiles(List<File> files, Map<String, Set<Word>> targetMap) throws FileNotFoundException {
		// process each file
		for (File file : files) {
			try (Scanner scanner = new Scanner(file))
			{
				if (!scanner.hasNextLine()) continue; // file is empty go to next file
				
				String category = scanner.nextLine(); // first line is category
				targetMap.putIfAbsent(category, new HashSet<>());
				while (scanner.hasNextLine()) {
					String[] parts = scanner.nextLine().split("\\|");
					String text = parts[0];
					String hint = parts[1];
					Word word = new Word(text, hint);
					targetMap.get(category).add(word);
				}
			}
		}
	}
}
