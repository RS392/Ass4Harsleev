package ass4;

public class Element {

	private String key;
	private String value;
	
	public Element(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
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
