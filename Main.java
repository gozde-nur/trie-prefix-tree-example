package sozluk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) {

		read();

		Scanner input = new Scanner(System.in);
		ArrayList<String> words= new ArrayList<>();
		BufferedReader inputStream = null;
		Trie t = new Trie();
		System.out.println("Words are loading , please wait...");
		try {
			inputStream = new BufferedReader(new FileReader("C:\\sozluk.txt"));
			String line;
			while ((line = inputStream.readLine()) != null) {
				words.add(line);
			}
		} catch (Exception e) {

		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
		System.out.println("Dictionary loaded.");
		String wordEntered = ".*Attempt.*";

		do {
			System.out.println("Type a word and press enter..");
			wordEntered = ".*" + input.nextLine().toUpperCase() + ".*";
			System.out.println("Possible Words");

			for (String data : words) {
				if (data.matches(wordEntered)) {
					System.out.println(data);
				}
			}
		} while (wordEntered.length() != 0);
	}

	static void read() {

	}
}

class Trie {

	Node root;

	Trie() {
		root = new Node();
	}

	void add(String word) {

		Node tmp = root;

		for (int i = 0; i < word.length(); i++) {

			char character = word.charAt(i);

			if (tmp.children[(int) character] == null) {
				tmp.children[(int) character] = new Node();
			}

			tmp = tmp.children[(int) character];

		}

		tmp.end = true;

	}

	boolean find(String word) {

		Node tmp = root;

		for (int i = 0; i < word.length(); i++) {

			char character = word.charAt(i);

			if (tmp.children[(int) character] != null) {

				tmp = tmp.children[(int) character];

			} else {
				return false;
			}
		}

		if (tmp != null && tmp.end)
			return true;

		return false;
	}

}

class Node {

	Node children[] = new Node[256];
	boolean end;

	Node() {
		end = false;
		for (int i = 0; i < children.length; i++)
			children[i] = null;
	}

}
