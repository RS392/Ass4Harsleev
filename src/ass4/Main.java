/* By: Radu Alexandru Saghin ((27667086) and Ashley Lee (26663486)
 * COMP 352, Assignment 4
 * 
 * Tests to see if MyHashTable works like the hash table ADT. Includes the answers to programming questions B and C
 */

package ass4;
import java.io.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		// Question b) for the programming assignment
		
		questionB('N','Q',10, 0.7, 2.0);	
		questionB('N','D',16, 0.8, 1.7);
		questionB('A','Q',20, 0.5, 1.1);
		questionB('A','D',5, 0.76, 2.5);
		
		
		
		// Question c) for the programming assignment
		questionC(1,'A','Q',100); // first set of strings
		questionC(1,'N','Q',100);
		questionC(1,'A','D',100);
		questionC(1,'N','D',100);
		
		questionC(2,'A','Q',100); // second set of strings
		questionC(2,'N','Q',100);
		questionC(2,'A','D',100);
		questionC(2,'N','D',100);
		
		questionC(2,'N','D',450000);
		
	}
	
	// used to answer part B of the programming questions
	public static void questionB(char emptyType, char collisionType, int size, double loadFactor, double factorOrNumber) {
		MyHashTable t = new MyHashTable();
		
		t.setEmptyMarkerScheme(emptyType);
		t.setCollisionType(collisionType);
		t.init(size);
		t.setRehashThreshold(loadFactor);
		t.setRehashFactor(factorOrNumber);
		t.put("Tony", "Tony");
		t.put("Riki", "Riki");
		t.put("Chiupap", "Chiupap");
		t.put("Tinker", "Tinker");
		t.put("Cancer", "Cancer");
		t.put("Betty Spaghetti", "Betty Spaghetti");
		t.put("Sassy Rubick", "Sassy Rubick");
		t.put("Saladar", "Saladar");
		t.put("Kebab", "Kebab");
		t.put("nukelicious", "nukelicious");
		t.put("Skellington", "Skellington");
		t.put("pizza-khaleesi", "pizza-khaleesi");
		t.put("Wilbur Bot", "Wilbur Bot");
		t.put("asdfghjkl", "asdfghjkl");
		t.put("gawshzilla", "gawshzilla");
		t.put("Defrosted icecream", "Defrosted ice cream");
		
		// uncomment to see where every Element is stored in the table
		// t.printContents();
		System.out.println("");
		
		t.remove("Tony");
		t.remove("Kebab");
		t.remove("Skellington");
		
		// uncomment to see where every Element is stored in the table after removal
		// t.printContents();
	
		t.printHashTableStatistics();
		
	}
	
	// used to answer part C of the programming question
	public static void questionC(int fileNumber, char emptyType, char collisionType, int size) {
		Scanner in = null;
		try {
			String fileName = "hash_test_file" + fileNumber + ".txt";
		in = new Scanner(new FileReader(fileName));
		} catch(FileNotFoundException e) {
			System.out.println("Cannot open file");
		}
		MyHashTable t = new MyHashTable();
		
		t.setEmptyMarkerScheme(emptyType);
		//t.init(735172);
		t.init(size);
		t.setRehashThreshold(0.5);
		t.setRehashFactor(2.1);
		t.setCollisionType(collisionType);
		String[] stringsFromFile = null;
		
		if (fileNumber == 1)
			stringsFromFile = new String[235886];
		else if(fileNumber == 2)
			stringsFromFile = new String[227126];
		int i = 0;
		while (in.hasNextLine()) {
			String s = in.nextLine();
			stringsFromFile[i] = s;
			++i;
		}
		//i)
		put(t,stringsFromFile,0,1000);
		put(t,stringsFromFile,1000,3000);
		put(t,stringsFromFile,3000,5000);
		put(t,stringsFromFile,5000,10000);
		put(t,stringsFromFile,10000,50000);
		put(t,stringsFromFile,50000,100000);
		put(t,stringsFromFile,100000,150000);
		put(t,stringsFromFile,150000,200000);
		put(t,stringsFromFile,200000,stringsFromFile.length);
		
		//ii)
		System.out.println("Strings from the file");

		//t.printHashTableStatistics();
		//removing 10k strings
		
		for (int j = 0; j < 10000; ++j) {
			t.remove(stringsFromFile[j]);
			
		}
		System.out.println("After removing 10k elements: ");
		//t.printHashTableStatistics();
		
		//getting all strings strings
		for(int j = 0; j < stringsFromFile.length; ++j) {
			t.get(stringsFromFile[j]);
		}
		
		//adding the 10k strings back
		for (int j = 0; j < 10000; ++j) {
			t.put(stringsFromFile[j], stringsFromFile[j]);
		}
		
		//getting all strings again
		
		for (int j = 0; j < stringsFromFile.length; ++j) {
			t.get(stringsFromFile[j]);
		}
		
		t.printHashTableStatistics();
		in.close();
		
	}
	public static void put(MyHashTable t, String[] strings,int start, int max) {
		for (int i = start; i < max; ++i) {
			String s = strings[i];
			t.put(s,s);
		}
	}
}
