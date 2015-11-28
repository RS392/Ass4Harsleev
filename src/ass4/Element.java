/* By: Radu Saghin and Ashley Lee (26663486)
 * COMP 352, Assignment 4
 * 
 * The Hash table implemented will store objects of type Element, where each Element will two take string variables:
 * 	1) The key string, so that it can be mapped to the hash table
 * 	2) The value string which takes the actual value of what needs to be stored in the hash table
 */

package ass4;

public class Element {

	private String key;
	private String value;
	private int calculatedAtSize;
	
	// constructor
	public Element(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public Element() {
		this.key = "";
		this.value = "";
	}
	
	// getter for the key
	public String getKey() {
		return key;
	}
	public int getCalculatedAtSize() {
		return calculatedAtSize;
	}
	public void setCalculatedAtSize(int size) {
		calculatedAtSize = size;
	}

	// setter for the key
	public void setKey(String key) {
		this.key = key;
	}

	// getter for the value
	public String getValue() {
		return value;
	}

	// setter for the value
	public void setValue(String value) {
		this.value = value;
	}
	
	// toString that outputs the key number and the value stored into the hash table
	public String toString() {
		String s;
		s = "key " + getKey() + " and value " + getValue();
		return s;
	}
}
