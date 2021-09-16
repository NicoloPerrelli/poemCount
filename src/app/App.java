package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
class WordsObj {
	String wordBank;
	int wordCount;

	public WordsObj(String wordBank, int wordCount) {
		this.wordBank = wordBank;
		this.wordCount = wordCount;
	}

	public String getWordBank() {return this.wordBank;}
	public void setWordBank(String word) {this.wordBank = word;}

	public int getWordCount() {return this.wordCount;}
	public void setWordCount(int number) {this.wordCount = number;}

	public String toString() {return this.wordBank + " " + this.wordCount;}
}

class SortCount implements Comparator<WordsObj> {
	public int compare(WordsObj a, WordsObj b){return a.wordCount - b.wordCount;}
}
//  <=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=->
//<=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=->
//  <=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=->

public class App {

	static void wordCount(String[] str) {
		Boolean newWordbBoolean;
		String word;
		List<WordsObj> wb = new ArrayList<WordsObj>();

		for (int i = 0; i < str.length; i++) {
			// Grab Word
			word = str[i];

			// Scrub Word of Non-alphanumeric
			word = word.replaceAll("[^a-zA-Z0-9]", "");

			//set bool to true till proven otherwise for this word.
			newWordbBoolean=true;
			//System.out.println(word+" word");
			for (WordsObj toss: wb) {

				if (toss.getWordBank().equals(word)) {
					newWordbBoolean=false;
					toss.setWordCount(toss.getWordCount()+1);
					break;
				}
			}

			//if  word is new add it and reset the key
			if (newWordbBoolean) {
				//System.out.print("New ");
				WordsObj newThing = new WordsObj(word,1);
				wb.add(newThing);
			}
		}
		//for (WordsObj asdf: wb) {System.out.println(asdf);}//unsorted
		Collections.sort(wb, new SortCount());
		for (WordsObj asdf: wb) {System.out.println(asdf);}//sorted
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Start Count");

		//move data from .txt file
		Scanner sc = new Scanner(new File("\\C:\\Users\\Nicolo Perrelli\\Desktop\\poemWordCount\\src\\app\\poemText.txt"));
		List<String> lines = new ArrayList<String>();

		while (sc.hasNext()) {
			//for cleaning up edge casses
			String splitIn = sc.next().replaceAll("—", " ");

			//take edge case into an array for seperate inserts with checks to filter out nulls and " "'s
			if (splitIn.contains(" ")) {
				String[] splitWords = splitIn.split(" ", 2);
				if (splitWords[0] != " " || !splitWords[0].isEmpty()) {lines.add(splitWords[0]);}
				if (splitWords[1] != " " || !splitWords[1].isEmpty()) {lines.add(splitWords[1]);}}
			else if (splitIn != " " || !splitIn.isEmpty()) {lines.add(splitIn);}
			else{System.out.println("SOMETHING DIDN'T FIT");}
		}

		String[] str = lines.toArray(new String[0]);

		wordCount(str);
		System.out.println("Done!");
	};
}