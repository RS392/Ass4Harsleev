/* By: Radu Alexandru Saghin ((27667086) and Ashley Lee (26663486)
 * COMP 352, Assignment 4
 * 
 * The implementation of the Hash Table ADT. It gives a key value to each value of an Element, and places them into an array
 *  based on the key value. When collisions occur, two types of collision handling are implemented for the user to select: 
 *  quadratic probing and double hashing.
 *  
 *  Additional cases are handled, such as setting the marker scheme and adjusting table sizes to fit the number of elements added.
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
	
	// getter for the hash table
	public Element[] getHashTable() {
		return hashTable;
	}
	
	// 
	public void setEmptyMarkerScheme(char type) {
		if(type == 'A' || type == 'N' || type == 'R')
			emptyType = type;
		else
			System.out.println("Cannot accept any other character");
	}
	
	// initializes the hash table with objects of Element
	public void init(int size) {
		hashTable = new Element[size];
		for (int i = 0; i < size; ++i) {
			if (emptyType == 'A' || emptyType == 'N') {
				hashTable[i] = new Element();
				hashTable[i].setKey("AVAILABLE");
			}
			
		}
		numberOfElements = 0;
		p = 15485863;
		//a = (int) (Math.random() * (p-1));
		//b = (int) (Math.random() * (p-1));
		
		a = 1;
		b = 0;
		
	}
	
	// takes two strings, and puts them into the hash table as an object of Element. Keeps track of the number of Elements in the hash table, as well.
	public void put(String key, String value) {
		checkForResize();
		Element ele = new Element(key, value);
		putIn(ele, 33);
		numberOfElements++;
	}
	
	
	private void putIn(Element ele, int z) {

		
		compressHash(keyAsInteger(ele.getKey()), ele);
		
	}
	
	// sets the collision type if there is collision handling with the keys
	private int keyAsInteger(String key) {
		if (collisionType == 'Q')
			return keyAsIntegerQH(key);
		else if (collisionType == 'D')
			return keyAsIntegerDH(key);
		else
			return 0;
	}
	
	// turns the string into an integer value that would be used as the key (for quadratic probing)
	private int keyAsIntegerQH(String key) {
		int hash=0;
		for(int i=0;i<key.length();i++)
		  hash = 33*hash + key.charAt(i); // hash code map that turns the string into a key value integer
		
		if (hash < 0)
			hash = -hash; // fixes the error if the key turns out to be a negative number
		
		int size = hashTable.length;
		int f = hash;
		if (f < 0) // fixes the error if the key turns out to be a negative number
			f = -f;
		int index = f % size; // the hash function for quadratic probing
		
		return index;
			
	}
	
	// turns the string into an integer value that would be used as the key (for double hashing)
	private int keyAsIntegerDH(String key) {
		int hash=0;
		for(int i=0;i<key.length();i++)
		  hash = 33*hash + key.charAt(i); // hash code map that turns the string into a key value integer
		
		if (hash < 0)
			hash = -hash; // handles the case when the key value from the equation above turns out to be negative
		
		int size = hashTable.length;
		int f = hash;
		if (f < 0)
			f = -f; // fixes the error if the key turns out to be a negative number
		int index = f % size; // the first hash function for double hashing (same as the quadratic hash function)
		
		return index;
	}
	
	// the second hash function for double hashing
	private int keyAsInteger2ndDH(String key) {
		int hash=0;
		for(int i=0;i<key.length();i++)
		  hash = 33*hash + key.charAt(i); // hash code map
		
		if (hash < 0)
			hash = -hash;
		
		int size = hashTable.length;
		int f = hash*2 + 1; // second hash function
		if (f < 0)
			f = -f;
		int index = f % size;//f % size
		
		return index;
		
	}
	
	// gets the value from a cell in the hash table
	public String get(String key) {
		
		
		int	i = keyAsInteger(key);
		
		int p = 0;
		int j = 1;
		while (p <= hashTable.length) {
			
			Element ele = hashTable[i];
			
			//  indicates if the cell is empty with the AVAILABLE marker
			if (ele.getKey().equals("AVAILABLE")) {
				return ele.getKey();
			}
			else if (ele.getKey().equals(key)) {
				return ele.getValue();
				
			}
			else {
				if (collisionType == 'Q') { // collision handling for quadratic probing gets handle here
					i = (i + j*j) % hashTable.length;
					if (i < 0)
						i = -i;
					j++;
					p++;
				} else if (collisionType == 'D') { // collision handling for double hashing gets handled here
					i = (i + j * keyAsInteger2ndDH(key)) % hashTable.length;
					if (i < 0)
						i = -i;
					++j;
					++p;
				}
			}
		}
		return "AVAILABLE";
	}

	// removes a specified value from the hash table
	public String remove(String key) {
		
		int i = keyAsInteger(key);
		
		int p = 0;
		int j = 1;

		while (p <= hashTable.length) {
		
			Element ele = hashTable[i];
			
			// if it's already empty, then nothing happens
			if (ele.getKey().equals("AVAILABLE") ) {
				
				return ele.getKey();
			}
			// removes the old value, but replaces it with a -, followed by the old value to indicate it has been removed
			else if (ele.getKey().equals(key)) {
				if (emptyType == 'N') {
				hashTable[i].setKey("- " + hashTable[i].getKey());
				hashTable[i].setValue("- " + hashTable[i].getValue());
				}
				else {
					hashTable[i].setKey("AVAILABLE");
					hashTable[i].setValue("");
				}
				numberOfElements--; // because an element has been removed, the number of Elements in the hash table decreases
				return ele.getValue();
				
			}
			// collision handling if it comes to the case
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
	
	// 
	private void compressHash(int index, Element ele) {
		if (hashTable[index].getKey().equals("AVAILABLE") ) {
			
			hashTable[index] = ele;
		}
		else {
			handleCollision(index, ele);
		}
	}
	
	// setter for the collision type to be handled
	public void setCollisionType(char type) {
		
		collisionType = type;
	}
	
	// different collision type handling depending on the collision type set
	private void handleCollision(int index, Element ele) {
		if (collisionType == 'Q')
			quadHandle(index,ele);
		else if (collisionType == 'D')
			doubleHashHandle(index, ele);
	}
	
	// collision handling for double hashing
	private void doubleHashHandle(int index, Element ele) {
		
		int j = 1;
		int newIndex = index;
		
		// when there needs to be handling once or more
		do {
			++numberOfCollisions;
			newIndex = index + j*(keyAsInteger2ndDH(ele.getKey())); 
			++j;
			if (newIndex < 0)
				newIndex = -newIndex;	// handling for a negative key
			// makes it into a circular array to consider when the new index after collision handling is out of bounds
			if(newIndex >= hashTable.length) {
				newIndex = newIndex % hashTable.length;
			}

		} while(!hashTable[newIndex].getKey().equals("AVAILABLE"));
		
		hashTable[newIndex] = ele;

	}
	
	// collision handling for quadratic probing
	private void quadHandle(int index, Element ele) {
		
				int j = 1;
				int newIndex = index;
				
				do {
					++numberOfCollisions;
					newIndex = index + j*j;
					++j;
				
					// makes it into a circular array to consider when the new index after collision handling is out of bounds
					if(newIndex >= hashTable.length) {
						newIndex = newIndex % hashTable.length;
					}
					
				} while(!hashTable[newIndex].getKey().equals("AVAILABLE"));
				
				hashTable[newIndex] = ele;
				
	}
	
	// checks to see what's stored in every index of the table
	public void printContents() {
		for(int i = 0; i < hashTable.length; ++i) {
			System.out.println("Element at index " + i + " has the " + hashTable[i]);
		}
	}
	
	// setter for the rehash threshold
	public void setRehashThreshold(double loadFactor) {
	
		rehashThreshold = loadFactor;
	}
	
	// setter for the rehash factor
	public void setRehashFactor(double factorOrNumber) {
		// integer case
		rehashFactor = factorOrNumber;
	
	}
	
	// checks to see if the table needs to be resized based on whether the hash factor is greater than the rehash threshold
	public void checkForResize() {
		int size = hashTable.length;
		double hashFactor = numberOfElements/(double)size;
		if ( hashFactor >= rehashThreshold)
			updateTableSize();
	}
	
	// updates the table size
	private void updateTableSize() {
		
		int oldLength = hashTable.length;
		int newLength = 0;
		// if the setRehashThreshold() has an integer set, then the table increases by the integer size given
		if( rehashFactor == (int)rehashFactor) {
			newLength = oldLength + (int)rehashFactor;
		}
		else { // factor case, if setRehashThreshold is given a real number, then the size increases to the old size multiplied by the rehash factor
			newLength = (int)(rehashFactor * oldLength);
		}
		// creates a new table with the new size, and sets all the cells to AVAILABLE (empty)
		 Element[] oldHashTable = hashTable;
		 hashTable = new Element[newLength];
		 for (int i = 0; i < newLength; ++i) {
			hashTable[i] = new Element();
		    hashTable[i].setKey("AVAILABLE");
		 }
		
		 // puts all the objects that were stored from the old sized table to the new one
		 for (int i = 0; i < oldLength; ++i) {
			 String s = oldHashTable[i].getKey();
			 if (!s.equals("AVAILABLE") && !s.substring(0, 1).equals("- ")) {
				 --numberOfElements;
				put(s, s);
			 }
		 }
		 
	}
	// prints statistics for the hash table
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
	
	// resets the statistics for the hash table
	public void resetHashTableStatistics() {
		numberOfElements = 0;
		numberOfCollisions = 0;
		emptyType = ' ';
		collisionType = ' ';
		rehashFactor = 0;
		rehashThreshold = 0;
	}
}
