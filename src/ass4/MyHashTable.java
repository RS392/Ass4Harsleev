/* By: Radu Saghin and Ashley Lee (26663486)
 * COMP 352, Assignment 4
 * 
 * The implementation of the Hash Table ADT. It gives a key value to each value of an Element, and places them into an array
 *  based on the key value. When collisions occur, two types of collision handling are implemented for the user to select: 
 *  quadratic probing and double hashing.
 */


package ass4;

public class MyHashTable {

	Element[] hashTable;
	int numberOfElements;
	int a;
	int b;
	int p;
	char collisionType;
	char emptyType;
	double rehashFactor;
	double rehashThreshold;
	int numberOfCollisions;
	
	
	int sizeChanges;
	
	public Element[] getHashTable() {
		return hashTable;
	}
	public void setEmptyMarkerScheme(char type) {
		if(type == 'A' || type == 'N' || type == 'R')
			emptyType = type;
		else
			System.out.println("Cannot accept any other character");
	}
	public void init(int size) {
		hashTable = new Element[size];
		for (int i = 0; i < size; ++i) {
			if (emptyType == 'A' || emptyType == 'N') {
				hashTable[i] = new Element();
				hashTable[i].setKey("AVAILABLE");
			}
			//hashTable[i] = null; // that means it's empty
		}
		numberOfElements = 0;
		p = 15485863;
		//a = (int) (Math.random() * (p-1));
		//b = (int) (Math.random() * (p-1));
		
		a = 1;
		b = 0;
		
	}
	
	public void put(String key, String value) {
		checkForResize();
		Element ele = new Element(key, value);
		putIn(ele, 33);
		numberOfElements++;
		//System.out.println("Is tony still here? " + ele.getKey());
	}
	
	private void putIn(Element ele, int z) {

		
		compressHash(keyAsInteger(ele.getKey()), ele);
		
	}
	
	private int keyAsInteger(String key) {
		if (collisionType == 'Q')
			return keyAsIntegerQH(key);
		else if (collisionType == 'D')
			return keyAsIntegerDH(key);
		else
			return 0;
	}
	
	private int keyAsIntegerQH(String key) {
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
		int index = f % size;//f % size
		
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
		int f = hash;
		if (f < 0)
			f = -f;
		int index = f % size;//f % size
		if(key.equals("Tinker2")) {
			System.out.println("index: " + index);
		}
		return index;
	}
	private int keyAsInteger2ndDH(String key) {
		int hash=0;
		for(int i=0;i<key.length();i++)
		  hash = 33*hash + key.charAt(i);
		//System.out.println("hash is: " + hash);
		if (hash < 0)
			hash = -hash;
		
		int size = hashTable.length;
		int f = hash*2 + 1;
		if (f < 0)
			f = -f;
		int index = f % size;//f % size
		
		return index;
		
	}
	public String get(String key) {
		
		
		int	i = keyAsInteger(key);
		
		int p = 0;
		int j = 1;
		while (p <= hashTable.length) {
			
			Element ele = hashTable[i];
			if (ele.getKey().equals("AVAILABLE")) {
				return ele.getKey();
			}
			else if (ele.getKey().equals(key)) {
				return ele.getValue();
				
			}
			else {
				if (collisionType == 'Q') {
					i = (i + j*j) % hashTable.length;
					if (i < 0)
						i = -i;
					j++;
					p++;
				} else if (collisionType == 'D') {
					i = (i + j * keyAsInteger2ndDH(key)) % hashTable.length;
					if (i < 0)
						i = -i;
					++j;
					++p;
				}
			}
		}
		// possible source of error
		return "AVAILABLE";
		//return hashTable[keyAsInteger(key)];
	}
	
	public String remove(String key) {
		
		boolean tinker2 = false;
		if (key.equals("Tinker2")) {
			tinker2 = true;
		}
		int i = keyAsInteger(key);
		
		int p = 0;
		int j = 1;
		//System.out.println("yoyo" + i);
		while (p <= hashTable.length) {
			if(tinker2 == true)
				System.out.println(i);
			//System.out.println(i);
			Element ele = hashTable[i];
			if (ele.getKey().equals("AVAILABLE") ) {
				
				return ele.getKey();
			}
			else if (ele.getKey().equals(key)) {
				if (emptyType == 'N') {
				hashTable[i].setKey("- " + hashTable[i].getKey());
				hashTable[i].setValue("- " + hashTable[i].getValue());
				}
				else {
					hashTable[i].setKey("AVAILABLE");
					hashTable[i].setValue("");
				}
				numberOfElements--;
				return ele.getValue();
				
			}
			else {
				if (collisionType == 'Q') {
				i = (i + j*j) % hashTable.length;
				if (i < 0)
					i = -i;
				
				j++;
				p++;
				
			} else if (collisionType == 'D') {
				i = (i + j * keyAsInteger2ndDH(key)) % hashTable.length;
				if (i < 0)
					i = -i;

				++j;
				++p;
			}
		}
		}
		// possible source of error
		return "AVAILABLE";
		}
	
	private void compressHash(int index, Element ele) {
		//System.out.println("Is tonyyyyyyyyyyyy still here? " + ele.getKey() + index);
		if (hashTable[index].getKey().equals("AVAILABLE") ) {
			
			hashTable[index] = ele;
		}
		else {
			handleCollision(index, ele);
			
		}
		//printContents();
		 
	}
	
	public void setCollisionType(char type) {
		
		collisionType = type;
	}
	private void handleCollision(int index, Element ele) {
		if (collisionType == 'Q')
			quadHandle(index,ele);
		else if (collisionType == 'D')
			doubleHashHandle(index, ele);
	}
	
	private void doubleHashHandle(int index, Element ele) {
		
		int j = 1;
		int newIndex = index;
		
		do {
			//System.out.println("waaaaaaaaaaaaaaaa");
			++numberOfCollisions;
			newIndex = index + j*(keyAsInteger2ndDH(ele.getKey()));
			++j;
			//System.out.println("newindex: " + newIndex);
			if (newIndex < 0)
				newIndex = -newIndex;
			if(newIndex >= hashTable.length) {
				newIndex = newIndex % hashTable.length;
			}
			//System.out.println(newIndex);
			//System.out.println("newindex: " + newIndex + " after modulo");
			if(ele.getKey().equals("Tinker2")) {
				System.out.println("tinker been handled yo: " + newIndex);
			}
		} while(!hashTable[newIndex].getKey().equals("AVAILABLE"));
		
		hashTable[newIndex] = ele;

	}
	private void quadHandle(int index, Element ele) {
		//System.out.println("handling collision at index: " + index);
				int j = 1;
				int newIndex = index;
				//System.out.println("should be handling chiupap" + ele.getKey());
				do {
					++numberOfCollisions;
					newIndex = index + j*j;
					++j;
				//	System.out.println("newindex: " + newIndex);
					
					if(newIndex >= hashTable.length) {
						newIndex = newIndex % hashTable.length;
					}
					//System.out.println("newindex: " + newIndex + " after modulo");
				} while(!hashTable[newIndex].getKey().equals("AVAILABLE"));
				
				hashTable[newIndex] = ele;
				
	}
	public void printContents() {
		for(int i = 0; i < hashTable.length; ++i) {
			System.out.println("Element at index " + i + " has the " + hashTable[i]);
		}
	}
	
	public void setRehashThreshold(double loadFactor) {
		//int size = hashTable.length;
		rehashThreshold = loadFactor;
		//double hashFactor = numberOfElements/(double)size;
		//System.out.println(hashFactor);
		//if (hashFactor >= loadFactor)
		//	setRehashFactor(2.1);
		
	}
	
	public void setRehashFactor(double factorOrNumber) {
		// integer case
		rehashFactor = factorOrNumber;
	
	}
	public void checkForResize() {
		int size = hashTable.length;
		double hashFactor = numberOfElements/(double)size;
		if ( hashFactor >= rehashThreshold)
			updateTableSize();
	}
	private void updateTableSize() {
		//System.out.println("resizing");
		int oldLength = hashTable.length;
		int newLength = 0;
		if( rehashFactor == (int)rehashFactor) {
			newLength = oldLength + (int)rehashFactor;
		} else { // factor case
			newLength = (int)(rehashFactor * oldLength);
		}
		 Element[] oldHashTable = hashTable;
		 hashTable = new Element[newLength];
		 for (int i = 0; i < newLength; ++i) {
			hashTable[i] = new Element();
		    hashTable[i].setKey("AVAILABLE");
		 }
		
		 for (int i = 0; i < oldLength; ++i) {
			 String s = oldHashTable[i].getKey();
			 if (!s.equals("AVAILABLE") && !s.substring(0, 1).equals("- ")) {
				 --numberOfElements;
				// System.out.println("changing position of: " + s);
				put(s, s);
			 }
		 }
		 
	}
	public void printHashTableStatistics() {
		String s = "";
		s = "Rehash Threshold: " + rehashThreshold + "\n";
		s += "Rehash Factor: " + rehashFactor + "\n";
		s += "Collision Handling Type: " + collisionType + "\n";
		s += "Empty Marcher Scheme Type: " + emptyType + "\n";
		s += "Size of the table: " + hashTable.length + "\n";
		s += "Number of elements in table: " + numberOfElements + "\n";
		s += "Load percentage: " + numberOfElements/(double)hashTable.length*100 + "\n";
		s += "Number of collisions accounted for: " + numberOfCollisions + "\n";
		System.out.println(s);
	}
	
	public void resetHashTableStatistics() {
		numberOfElements = 0;
		numberOfCollisions = 0;
		emptyType = ' ';
		collisionType = ' ';
		rehashFactor = 0;
		rehashThreshold = 0;
	}
}
