
package ass4;

public class MyHashTable {

	int[] hashTable;
	int a;
	int b;
	int p;
	public MyHashTable(int size) {
		hashTable = new int[size];
		for (int i = 0; i < size; ++i) {
			hashTable[i] = -1; // that means it's empty
		}
		p = 7;
		a = (int) (Math.random() * (p-1));
		b = (int) (Math.random() * (p-1));
		
	}
	
	public void put(String key) {
		putIn(key, 33);
	}
	
	private void putIn(String key, int z) {

		int hash=0;
		for(int i=0;i<key.length();i++)
		  hash = z*hash + key.charAt(i);
		//System.out.println("hash is: " + hash);
		if (hash < 0)
			hash = -hash;
		
		compressHash(hash);
		
	}
	
	private void compressHash(int hash) {
		int size = hashTable.length;
		
		//System.out.println("a is : " + a + " and b is: " + b);
		
		int f = hash*a+b;
		if (f < 0)
			f = -f;
		
		int index = (f % p) % size;
		
		
		/*
		System.out.println("hash: " + hash);
		System.out.println("first mod is: " + (hash*a + b) % p);
		System.out.println("index in compress is: " + index);
		*/
		//System.out.println("putting hash: " + hash + "at index: " + index);
		if (hashTable[index] == -1)
			hashTable[index] = hash;
		else
			handleCollision(index,hash);
		
		//printContents();
		 
	}
	
	private void handleCollision(int index,int hash) {
		//System.out.println("handling collision at index: " + index);
		int j = 1;
		int newIndex = index;
		
		
		do {
			newIndex = index + j*j;
			++j;
			System.out.println("newindex: " + newIndex);
			
			if(newIndex >= hashTable.length) {
				newIndex = newIndex % hashTable.length;
			}
			System.out.println("newindex: " + newIndex + " after modulo");
		} while(hashTable[newIndex] != -1);
		
		hashTable[newIndex] = hash;
		
	}
	
	public void printContents() {
		for(int i = 0; i < hashTable.length; ++i) {
			System.out.println("Thing at index: " + i + " is: " + hashTable[i]);
		}
	}
}
