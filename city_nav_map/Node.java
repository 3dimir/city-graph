package city_nav_map;

public class Node {

	//
	// 	INITIAL VARIABLE
	//
	private String id;
	
	
	//
	// 	GETTER
	//
	public String getID() { return id; }
	
	
	//
	// 	CONSTRUCTOR
	//
	public Node(String id) { this.id = id; }
	
	
	//
	// 	HASHMAP COMPARISON
	//
	// 	Requierd for HashMap usage - nodes considered equal if IDs match
	//
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		
		Node node = (Node) o;
		
		return id.equals(node.id);
	}
	
	
	@Override
	public int hashCode() { return id.hashCode(); }
	
	
	@Override
	public String toString() {
		return id;
	}
}
