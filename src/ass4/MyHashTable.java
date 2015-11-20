
package ass4;

public class MyHashTable {

	Element[] hashTable;
	int a;
	int b;
	int p;
	public MyHashTable(int size) {
		hashTable = new Element[size];
		for (int i = 0; i < size; ++i) {
			hashTable[i] = null; // that means it's empty
		}
		p = 7;
		//a = (int) (Math.random() * (p-1));
		//b = (int) (Math.random() * (p-1));
		
		a = 3;
		b = 4;
		
	}
	
	public void put(String key, String value) {
		Element ele = new Element(key, value);
		putIn(ele, 33);
	}
	
	private void putIn(Element ele, int z) {

		
		compressHash(keyAsInteger(ele.getKey()), ele);
		
	}
	
	private int keyAsInteger(String key) {
		int hash=0;
		for(int i=0;i<key.length();i++)
		  hash = 33*hash + key.charAt(i);
		//System.out.println("hash is: " + hash);
		if (hash < 0)
			hash = -hash;
		
		
		int size = hashTable.length;
		int f = hash*a+b;
		if (f < 0)
			f = -f;
		int index = (f % p) % size;
		
		return index;
	}
	
	public Element get(String key) {
		return hashTable[keyAsInteger(key)];
	}
	
	
	private void compressHash(int index, Element ele) {
		
		
		if (hashTable[index] == null)
			hashTable[index] = ele;
		else
			handleCollision(index, ele);
		
		//printContents();
		 
	}
	
	private void handleCollision(int index, Element ele) {
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
		} while(hashTable[newIndex] != null);
		
		hashTable[newIndex] = ele;
		
	}
	
	public void printContents() {
		for(int i = 0; i < hashTable.length; ++i) {
			System.out.println("Element at index " + i + " has the " + hashTable[i]);
		}
	}
}
