package ass4;

public class Element {

	private String key;
	private String value;
	private int calculatedAtSize;
	public Element(String key, String value) {
		this.key = key;
		this.value = value;
	}
	public Element() {
		
	}
	public String getKey() {
		return key;
	}
	public int getCalculatedAtSize() {
		return calculatedAtSize;
	}
	public void setCalculatedAtSize(int size) {
		calculatedAtSize = size;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString() {
		String s;
		s = "key " + getKey() + " and value " + getValue();
		return s;
	}
}
