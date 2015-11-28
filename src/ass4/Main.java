package ass4;
import java.io.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		questionD(1); // first set of strings
		questionD(2); // second set of strings
	}
	
	public static void questionD(int fileNumber) {
		Scanner in = null;
		try {
			String fileName = "hash_test_file" + fileNumber + ".txt";
		in = new Scanner(new FileReader(fileName));
		} catch(FileNotFoundException e) {
			System.out.println("Cannot open file");
		}
		MyHashTable t = new MyHashTable();
		
		t.setEmptyMarkerScheme('A');
		//t.init(300000);
		t.init(100);
		t.setRehashThreshold(0.5);
		t.setRehashFactor(2.1);
		t.setCollisionType('D');
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
		if (fileNumber == 1)
			put(t,stringsFromFile,200000,235886);
		else if (fileNumber == 2)
			put(t,stringsFromFile,200000,227126);
		
		//t.printContents();
		//ii)
		for (int j = 0; j < 10000; ++j) {
			System.out.println("removing key: " + t.remove(stringsFromFile[j]));
			
		}
		
		t.printHashTableStatistics();
		in.close();
		
	}
	public static void put(MyHashTable t, String[] strings,int start, int max) {
		for (int i = start; i < max; ++i) {
			String s = strings[i];
			t.put(s,s);
			t.checkForResize();
		}
	}
}
