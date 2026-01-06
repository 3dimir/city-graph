package city_nav_map;

public class Edge {

	//
	// 	INITIAL VARIABLES
	//
	private Node destination;
	private int weight;
	
	
	//
	// 	GETTERS
	//
	public Node getDestination() { return destination; }
	public int getWeight() { return weight; }
	
	
	//
	// 	CONSTRUCTOR
	//
	public Edge(Node destination, int weight) {
		this.destination = destination;
		this.weight = weight;
	}
	
	
	public String toString() {
		return String.format("Distance to %s: (%d)", destination, weight);
	}
}
