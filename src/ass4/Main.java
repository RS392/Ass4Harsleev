package ass4;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//LOOOOOOOOOOOOOOOL

		MyHashTable t = new MyHashTable(3);
		t.setCollisionType('D');
		t.put("abc", "abc");
		t.put("dce", "eeee");
		t.put("asf", "asf");
		t.printContents();
		System.out.println(t.get("dce"));
		t.remove("abc");
		t.setRehashThreshold(0.5);
		t.printContents();
		System.out.println("cacapeepee");
	}

}
