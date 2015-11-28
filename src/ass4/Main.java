package ass4;
import java.io.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	//	test();
		
		//b)
		
		
		//c)
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
	
	public static void test() {
		MyHashTable t = new MyHashTable();
		
		t.setEmptyMarkerScheme('N');
		t.init(7);
		t.setRehashThreshold(0.5);
		t.setRehashFactor(2.1);
		t.setCollisionType('D');
		t.put("Tony", "Tony");
		t.put("Riki", "Riki");
		t.put("Chiupap", "Chiupap");
		t.put("Tinker", "Tinker");
		t.put("Tony1", "Tony1");
		t.put("Riki1", "Riki1");
		t.put("Chiupap1", "Chiupap1");
		t.put("Tinker1", "Tinker1");
		t.put("Tony2", "Tony2");
		t.put("Riki2", "Riki2");
		t.put("Chiupap2", "Chiupap2");
		t.put("Tinker2", "Tinker2");
		t.put("Tony3", "Tony3");
		t.put("Riki3", "Riki3");
		t.put("Chiupap3", "Chiupap3");
		t.put("Tinker3", "Tinker3");
		
		t.remove("Tony");
		t.remove("Riki");
		t.remove("Chiupap");
		t.remove("Tinker");
		for (int i = 1; i < 4; ++i) {
			t.remove("Tony" + i);
			t.remove("Riki" + i);
			t.remove("Chiupap" + i);
			t.remove("Tinker" + i);
		}
		t.printContents();
		
	}
	
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
		
		//t.printContents();
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
