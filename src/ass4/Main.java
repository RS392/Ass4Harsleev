package ass4;
import java.io.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//LOOOOOOOOOOOOOOOL
		Scanner in = null;
		try {
		in = new Scanner(new FileReader("hash_test_file1.txt"));
		} catch(FileNotFoundException e) {
			System.out.println("loooooooooooooool");
		}
		
		
		MyHashTable t = new MyHashTable();
		
		t.setEmptyMarkerScheme('A');
		t.init(100);
		t.setRehashThreshold(0.5);
		t.setRehashFactor(2.1);
		t.setCollisionType('D');
		while(in.hasNextLine()) {
			String s = in.nextLine();
			t.put(s, s);
			t.checkForResize();
		}
		//System.out.println("it took " + (endTime-startTime) + " nanoseconds baby");
		//t.put("abc", "abc");
		//t.put("dce", "eeee");
		//t.put("asf", "asf");
		
		
		//t.printContents();
		//t.setRehashThreshold(0.5);
		//t.printContents();
		t.printHashTableStatistics();
		System.out.println("cacapeepee");
		
		in.close();
	}

}
