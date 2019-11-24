package wordCount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Count {
	static HashMap<String, Integer> frequency = new HashMap<String, Integer>();
	static int totalcount = 0;
	static int removeReturnSpaces = -1;
	static String max;
	static int index = 0;
	static HashMap<Integer, String> sentencelist = new HashMap<Integer, String>();
	public static void main(String[] args) throws IOException {
		//Opens the file
		 BufferedReader br = new BufferedReader(new FileReader("passage.txt"));
		 String line;
		 //reads the first paragraph of file
		 line = br.readLine();
		 String[] words;
		 String[] sentences;
		 //While loop to read the rest of the file
		while(line != null){
			//breaks lines into words by spaces
			words = line.split(" ");
			//breaks lines into sentences by periods
			sentences = line.split("\\.");
			removeReturnSpaces++;
			//counts all the words and their  occurrences
			for (int i = 0; i < words.length; i++) {
				totalcount++;
				//turns each word to lowercase
				words[i] = words[i].toLowerCase();
				//removes periods if attached to word
				if(words[i].length() > 0 && words[i].charAt(words[i].length() - 1) == '.') {
					words[i] = words[i].substring(0, words[i].length() - 1);
				}
				//removes colons if attached to word
				if(words[i].length() > 0 && words[i].charAt(words[i].length() - 1) == ':') {
					words[i] = words[i].substring(0, words[i].length() - 1);
				}
				//removes ending quotations if attached to word
				if(words[i].length() > 0 && words[i].charAt(words[i].length() - 1) == '"') {
					words[i] = words[i].substring(0, words[i].length() - 1);
				}
				//removes beginning quotations if attached to word
				if(words[i].length() > 0 && words[i].charAt(0) == '"') {
					words[i] = words[i].substring(1);
				}
				//removes commas if attached to word
				if(words[i].length() > 0 && words[i].charAt(words[i].length() - 1) == ',') {
					words[i] = words[i].substring(0, words[i].length() - 1);
				}
				if(frequency.containsKey(words[i])) {
					int temp = frequency.get(words[i]);
					temp++;
					frequency.replace(words[i], temp);
				}else {
					frequency.put(words[i], 1);
				}
			}
			//Store the sentences
			for (int i = 0; i < sentences.length; i++) {
				sentencelist.put(index, sentences[i]);
				index++;
			}
			line = br.readLine();
			}
		totalcount -= removeReturnSpaces; 
		System.out.println("The total word count is " + totalcount);
		System.out.println("The top ten words from most occurring to 10th most occurring are:");
		//returns top ten words 
		for (int i = 0; i < 10; i++) {
			String temp= "";
			int maxfq = 0;
			for (String string : frequency.keySet()) {
				if(frequency.get(string) >= maxfq) {
					temp = string;
					maxfq = frequency.get(string);
				}
			}
			int t = i + 1;
			System.out.println(t + "." + temp);
			if(i == 0) {
				max = temp;
			}
			frequency.remove(temp);
		}
		//Gets the last sentence that contains the max occurring word
		String lastSentence = "";
		for (Integer i : sentencelist.keySet()) {
			if(sentencelist.get(i).contains(max)) {
				lastSentence = sentencelist.get(i);
			}
				
		}
		System.out.println("Last sentence with most occurring word is:");
		System.out.println(lastSentence + ".");
		br.close();
	}

}
