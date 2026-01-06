package city_nav_map;

import java.util.*;

public class Graph {

	//
	// 	DATA STRUCTURES
	//
	private Map<String, Node> nodes;				// Maps ID -> Node
	private Map<Node, List<Edge>> adjacencyList;	// Node -> Corresponding edges
	
	
	//
	// 	CONSTRUCTOR
	//
	public Graph () {
		this.nodes = new HashMap<>();
		this.adjacencyList = new HashMap<>();
	}
	
	
	//
	// 	UTILITY METHODS:
	//
	// 	Just here to improve readability.
	//
	public boolean hasNode(String id) {
		return nodes.containsKey(id);
	}
	
	
	public Node getNode(String id) {
		return nodes.get(id);
	}
	
	
	public List<Edge> getNeighbors(Node node) {
		return adjacencyList.get(node);
	}
	
	
	
	
	//
	// 	ADD METHODS:
	//
	// 	Adds intersections and roads
	// 	via user input.
	//
	public boolean addIntersection(String id) {
		if (hasNode(id)) return false;
		
		Node node = new Node(id);
		
		nodes.put(id, node);
		adjacencyList.put(node, new ArrayList<>());
		
		return true;
	}
	
	
	public boolean addRoad(String fromID, String toID, int weight, boolean isTwoWay) {
		if (!hasNode(fromID) || !hasNode(toID) || fromID.equalsIgnoreCase(toID)) return false;	// Double check
		
		Node fromNode = getNode(fromID);
		Node toNode = getNode(toID);
		
		// Add forward edge
		Edge edge = new Edge(toNode, weight);
		getNeighbors(fromNode).add(edge);
		
		// Adds reverse edge if two-way
		if (isTwoWay) {
			Edge revEdge = new Edge(fromNode, weight);
			getNeighbors(toNode).add(revEdge);
		}
		return true;
	}
	
	
	
	
	//
	// 	REMOVE METHODS:
	//
	// 	Removed intersections and roads
	// 	via user input, utilizing lambda.
	//
	public boolean removeIntersection(String id) {
		if (!hasNode(id)) return false;
		
		Node node = getNode(id);
		
		// 	Remove the node itself.
		nodes.remove(id);
		adjacencyList.remove(node);
		
		// 	Iterates through all values in the adjacencyList,
		// 	removing all edges connected to the deleted node.
		for (List<Edge> edgeList : adjacencyList.values()) {
			edgeList.removeIf(edge -> edge.getDestination().equals(node));
		}
		
		return true;
	}
	
	
	public boolean removeRoad(String fromID, String toID, boolean removeBothWays) {
		if (!hasNode(fromID) || !hasNode(toID) || fromID.equalsIgnoreCase(toID)) return false;
		
		Node fromNode = getNode(fromID);
		Node toNode = getNode(toID);
		
		// 	Checks if the edge exists, as well as
		//	removes it if it does.
		boolean hadRoad = getNeighbors(fromNode).removeIf(edge -> edge.getDestination().equals(toNode));
		
		// 	Removes the reversed edge if two-way.
		if (removeBothWays) getNeighbors(toNode).removeIf(edge -> edge.getDestination().equals(fromNode));
		
		return hadRoad;
	}
	
	
	
	
	//
	// 	PRINT METHODS - FORMATTED:
	//
	// 	Formated to display the intersection name
	//	as well as each intersection it is connected
	// 	to and the distance between them.
	//
	// 	For reachable intersections, first determine
	// 	if there are any before printing the LinkedList.
	// 	Since listReachableIntersections is inclusive,
	// 	print all intersections except itself.
	//
	public void displayMap() {
		if (nodes.isEmpty()) {
			System.out.println("\nERR: Map is empty.");
			return;
		}
		
		for (Node node : nodes.values()) {
			System.out.println("Intersection " + node + ":");
			
			List<Edge> edges = getNeighbors(node);
			
			if (edges.isEmpty()) System.out.println("    No outgoing roads.");
			
			else {
				for (Edge edge : edges) { System.out.println("    -> " + edge); }
			}
			
			System.out.println();
		}
	}
	
	
	public void displayReachableIntersections(String id) {
		HashSet<Node> reachable = listReachableIntersections(id);
		
		System.out.println("\nIntersection " + id + " reaches:");
		
		boolean foundAny = false;
		// Exclusive
		for (Node node : reachable) {
			if (!node.getID().equals(id)) {
				System.out.println("    -> " + node);
				foundAny = true;
			}
		}
		
		if (!foundAny) System.out.println("    No other Intersections.");
		
		System.out.println();
	}
	
	
	
	
	//
	// TRAVERSING ALGORITHMS - BFS:
	//
	// Both BFS and DFS work here, but
	// BFS makes more sense to me.
	//
	// Finds all intersections that are
	// reachable from the given input,
	// then returns them in a HashSet.
	//
	public boolean checkPathExists(String startID, String endID) {
		if (!hasNode(startID) || !hasNode(endID)) return false;
		
		Node startNode = getNode(startID);
		Node endNode = getNode(endID);
		
		// 	LinkedList functions as our queue
		LinkedList<Node> queue = new LinkedList<>();
		queue.add(startNode);
		
		// 	HashSet functions as our visited set
		HashSet<Node> visited = new HashSet<>();
		visited.add(startNode);		
		
		while (!queue.isEmpty()) {
			Node current = queue.remove();
			
			if (current.equals(endNode)) return true;
			
			for (Edge edge : getNeighbors(current)) {
				Node neighbor = edge.getDestination();
				
				if (!visited.contains(neighbor)) {
					visited.add(neighbor);
					queue.add(neighbor);
				}
			}
		}
		return false;
	}
	
	
	public HashSet<Node> listReachableIntersections(String startID) {
		if (!hasNode(startID)) return null;
		
		Node startNode = getNode(startID);
		
		LinkedList<Node> queue = new LinkedList<>();
		queue.add(startNode);
		
		HashSet<Node> visited = new HashSet<>();
		visited.add(startNode);
		
		while (!queue.isEmpty()) {
			Node current = queue.remove();
			
			for (Edge edge : getNeighbors(current)) {
				Node neighbor = edge.getDestination();
				
				if (!visited.contains(neighbor)) {
					visited.add(neighbor);
					queue.add(neighbor);
				}
			}
		}
		return visited;
	}
	
	
	
	
	//
	// 	UNIT TESTING
	//
	public int getNodeCount() {
		return nodes.size();
	}
	
	
	public boolean hasRoad (String fromID, String toID) {
		if (!hasNode(fromID) || !hasNode(toID)) return false;
		
		Node fromNode = getNode(fromID);
		Node toNode = getNode(toID);
		
		for (Edge edge : getNeighbors(fromNode)) {
			if (edge.getDestination().equals(toNode)) return true;
		}
		return false;
	}
}
