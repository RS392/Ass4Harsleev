
package ass4;

public class MyHashTable {

	Element[] hashTable;
	int numberOfElements;
	int a;
	int b;
	int p;
	char type;
	public MyHashTable(int size) {
		hashTable = new Element[size];
		for (int i = 0; i < size; ++i) {
			hashTable[i] = null; // that means it's empty
		}
		numberOfElements = 0;
		//p = 7;
		//a = (int) (Math.random() * (p-1));
		//b = (int) (Math.random() * (p-1));
		
		a = 1;
		b = 0;
		
	}
	
	public void put(String key, String value) {
		Element ele = new Element(key, value);
		putIn(ele, 33);
		numberOfElements++;
		//System.out.println(numberOfElements);
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
		int f = hash;//*a+b;
		if (f < 0)
			f = -f;
		int index = f % size;//f % p
		
		return index;
	}
	
	private int keyAsIntegerDH(String key) {
		int hash=0;
		for(int i=0;i<key.length();i++)
		  hash = 33*hash + key.charAt(i);
		//System.out.println("hash is: " + hash);
		if (hash < 0)
			hash = -hash;
		
		
		int size = hashTable.length;
		int f = hash*2+1;
		if (f < 0)
			f = -f;
		int index = f % size;//f % p
		
		return index;
	}
	
	public String get(String key) {
		
		int i = keyAsInteger(key);
		int p = 0;
		int j = 1;
		while (p <= hashTable.length) {
			
			Element ele = hashTable[i];
			if (ele == null) {
				return null;
			}
			else if (ele.getKey().equals(key)) {
				return ele.getValue();
				
			}
			else {
				i = (i + j*j) % hashTable.length;
				j++;
				p++;
			}
		}
		
		return null;
		//return hashTable[keyAsInteger(key)];
	}
	
	public String remove(String key) {
		

		int i = keyAsInteger(key);
		int p = 0;
		int j = 1;
		while (p <= hashTable.length) {
			
			Element ele = hashTable[i];
			if (ele == null) {
				return null;
			}
			else if (ele.getKey().equals(key)) {
				hashTable[i] = null;
				numberOfElements--;
				return ele.getValue();
				
			}
			else {
				i = (i + j*j) % hashTable.length;
				j++;
				p++;
			}
		}
		
		return null;
		}
	
	private void compressHash(int index, Element ele) {
		
		
		if (hashTable[index] == null)
			hashTable[index] = ele;
		else
			handleCollision(index, ele);
		
		//printContents();
		 
	}
	
	public void setCollisionType(char type) {
		
		this.type = type;
	}
	private void handleCollision(int index, Element ele) {
		if (type == 'Q')
			quadHandle(index,ele);
		else if (type == 'D')
			doubleHashHandle(index, ele);
	}
	
	private void doubleHashHandle(int index, Element ele) {
		
		int j = 1;
		int newIndex = index;
		int a = 2;
		int b = 1;
		
		do {
			newIndex = index + j*(keyAsIntegerDH(ele.getKey()));
			++j;
			System.out.println("newindex: " + newIndex);
			
			if(newIndex >= hashTable.length) {
				newIndex = newIndex % hashTable.length;
			}
			//System.out.println("newindex: " + newIndex + " after modulo");
		} while(hashTable[newIndex] != null);
		
		hashTable[newIndex] = ele;

	}
	private void quadHandle(int index, Element ele) {
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
					//System.out.println("newindex: " + newIndex + " after modulo");
				} while(hashTable[newIndex] != null);
				
				hashTable[newIndex] = ele;
				
	}
	public void printContents() {
		for(int i = 0; i < hashTable.length; ++i) {
			System.out.println("Element at index " + i + " has the " + hashTable[i]);
		}
	}
	
	public void setRehashThreshold(double loadFactor) {
		int size = hashTable.length;
		
		double hashFactor = numberOfElements/(double)size;
		System.out.println(hashFactor);
		if (hashFactor >= loadFactor)
			setRehashFactor(2.1);
		
	}
	
	public void setRehashFactor(double factorOrNumber) {
		// integer case
		
		int oldLength = hashTable.length;
		int newLength = 0;
		if( factorOrNumber == (int)factorOrNumber) {
			newLength = oldLength + (int)factorOrNumber;
		} else { // factor case
			newLength = (int)(factorOrNumber * oldLength);
		}
		 Element[] newHashTable = new Element[newLength];
		 for (int i = 0; i < oldLength; i++) {
			 newHashTable[i] = hashTable[i];
		 }
		this.hashTable = newHashTable;
		
	}
}
