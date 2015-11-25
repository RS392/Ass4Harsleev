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
		t.setCollisionType('D');
		double startTime = System.nanoTime();
		while(in.hasNextLine()) {
			String s = in.nextLine();
			t.put(s, s);
			t.setRehashThreshold(0.5);
		}
		double endTime = System.nanoTime();
		System.out.println("it took " + (endTime-startTime) + " nanoseconds baby");
		//t.put("abc", "abc");
		//t.put("dce", "eeee");
		//t.put("asf", "asf");
		
		
		//t.printContents();
		//t.setRehashThreshold(0.5);
		//t.printContents();
		System.out.println("cacapeepee");
		
		in.close();
	}

}
