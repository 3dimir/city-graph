package city_nav_map;

import java.util.Scanner;

public class Main {
	
	//
	//	INITIAL VARIABLES
	//
	private Graph graph = new Graph();
	private Scanner input = new Scanner(System.in);
	
	
	//
	//	UTILITY METHODS:
	//
	// 	buffer(): Allows a pop up to hang around before returning 
	// 	to menu, resulting in a better viewing experience.
	//	
	// 	yesNoPrompt(): Reusable Y/N prompt that loops until valid
	//	input. used for exitCheck() and two-way road confirmation.
	//
	// 	getString(): Reusability for pretty much every core method.
	//	Gets user input and checks for 'exit' input (exitCheck),
	//	returns null if user wants to return to menu.
	//
	private void buffer() {
		System.out.println("Press 'ENTER' to continue");
		input.nextLine();
		return;
	}
	
	
	private boolean yesNoPrompt(String prompt) {
		while (true) {
			System.out.println(prompt);
			System.out.print("Y/N > ");
			
			String choice = input.nextLine().trim();
			
			if (choice.equalsIgnoreCase("n")) return false; 
			if (choice.equalsIgnoreCase("y")) return true;
			
			System.out.print("\nERR: Please enter Y or N.");
		}
	}
	
	
	private boolean exitCheck(String exit) {
		if (exit.equalsIgnoreCase("exit")) return yesNoPrompt("\nReturn to menu?");
		return false;
	}
	
	
	private String getString(String prompt) {
		System.out.print(prompt);
		String id = input.nextLine().trim();
		
		if (exitCheck(id)) return null;	// Checks user input
		
		return id;
	}
	
	
	
	
	//
	//	CORE METHODS
	//
	private void addIntersection() {
		while (true) {
			String id = getString("Enter the intersection's ID:\n> ");
			
			if (id == null) return;
			
			if (graph.addIntersection(id)) {
				System.out.println("\nSuccessfully added Intersection!");
				break;
			}
			
			System.out.println("\nERR: Intersection already exists.");
			buffer();
		}
		buffer();
	}
	
	
	private void addRoad() {
		if (graph.getNodeCount() < 2) {
			System.out.println("\nERR: Not enough Intersections to create a Road");
			buffer();
			return;
		}
		
		String startID;
		while (true) {
			startID = getString("Select starting Intersection:\n> ");
			
			if (startID == null) return;
			if (graph.hasNode(startID)) break;
			
			System.out.println("\nERR: Intersection does not exist.");
			buffer();
		}
		
		String endID;
		while (true) {
			endID = getString("\nSelect destination:\n> ");
			
			if (endID == null) return;
			if (graph.hasNode(endID) && !endID.equalsIgnoreCase(startID)) break;
			
			if (!graph.hasNode(endID)) {
				System.out.println("\nERR: Intersection does not exist.");
				buffer();
			} else if (endID.equalsIgnoreCase(startID)) {
				System.out.println("\nERR: Cannot be same Intersection.");
				buffer();
			}
		}
		
		//	Weight is optional - accepts int or blank (default to 0).
		int distance;
		
		String weight;
		while (true) {
			weight = getString("\nOptional: Road distance (leave blank for default):\n> ");
			
			if (weight == null) return;
			
			if (weight.matches("\\d+")) {
				distance = Integer.parseInt(weight);
				break;
			}
			
			if (weight.isEmpty()) {
				distance = 0;
				break;
			}
			
			System.out.println("\nERR: Enter an integer value, otherwise leave blank");
			buffer();
		}
		
		boolean isTwoWay = yesNoPrompt("\nDoes the road go both ways?");
		
		graph.addRoad(startID, endID, distance, isTwoWay);
		
		System.out.println("\nSuccessfully added Road!");
		buffer();
	}
	
	
	private void removeIntersection() {
		if (graph.getNodeCount() == 0) {
			System.out.println("\nERR: No Intersections yet.");
			buffer();
			return;
		}
		while (true) {
			String id = getString("Enter the intersection's ID:\n> ");
			
			if (id == null) return;
			
			if (graph.removeIntersection(id)) {
				System.out.println("\nSuccessfully removed Intersection!");
				break;
			}
			
			System.out.println("\nERR: Intersection doesn't exist.");
			buffer();
		}
		buffer();
	}
	
	
	private void removeRoad() {
		if (graph.getNodeCount() < 2) {
			System.out.println("\nERR: Not enough Intersections to remove a Road");
			buffer();
			return;
		}
		
		String startID;
		while (true) {
			startID = getString("Select starting Intersection:\n> ");
			
			if (startID == null) return;
			if (graph.hasNode(startID)) break;
			
			System.out.println("\nERR: Intersection does not exist.");
			buffer();
		}
		
		String endID;
		while (true) {
			endID = getString("\nSelect destination:\n> ");
			
			if (endID == null) return;
			if (graph.hasNode(endID) && !endID.equalsIgnoreCase(startID)) break;
			
			if (!graph.hasNode(endID)) {
				System.out.println("\nERR: Intersection does not exist.");
				buffer();
			} else if (endID.equalsIgnoreCase(startID)) {
				System.out.println("\nERR: Cannot be same Intersection.");
				buffer();
			}
		}
		
		boolean removeBothWays = yesNoPrompt("\nIf applicable: Remove both directions?");
		
		if (graph.removeRoad(startID, endID, removeBothWays)) {
			System.out.println("\nSuccessfully removed Road!");
		} else {
			System.out.println("\nERR: No Road found between Intersections.");
		}
		buffer();
	}
	
	
	private void displayMap() {
		graph.displayMap();
		buffer();
	}
	
	
	private void checkPathExistence() {
		if (graph.getNodeCount() < 2) {
			System.out.println("\nERR: Not enough Intersections to check paths.");
			buffer();
			return;
		}
		
		String startID;
		while (true) {
			startID = getString("Select starting Intersection:\n> ");
			
			if (startID == null) return;
			if (graph.hasNode(startID)) break;
			
			System.out.println("\nERR: Intersection does not exist.");
			buffer();
		}
		
		String endID;
		while (true) {
			endID = getString("\nSelect destination:\n> ");
			
			if (endID == null) return;
			if (graph.hasNode(endID) && !endID.equalsIgnoreCase(startID)) break;
			
			if (!graph.hasNode(endID)) {
				System.out.println("\nERR: Intersection does not exist.");
				buffer();
			} else if (endID.equalsIgnoreCase(startID)) {
				System.out.println("\nERR: Cannot be same Intersection.");
				buffer();
			}
		}
		
		if (graph.checkPathExists(startID, endID)) {
			System.out.println("\nAt least one path exists from " + startID + " to " + endID + "!");
			
			if (yesNoPrompt("View?")) {
				graph.displayReachableIntersections(startID);
				buffer();
			} 
		} else {
			System.out.println("\nNo path exists from " + startID + " to " + endID + ".");
			buffer();
		}
	}
	
	
	private void listReachableIntersections() {
		if (graph.getNodeCount() < 2) {
			System.out.println("\nERR: Not enough Intersections.");
			buffer();
			return;
		}
		
		String id;
		while (true) {
			id = getString("Select Intersection:\n> ");
			
			if (id == null) return;
			if (graph.hasNode(id)) break;
			
			System.out.println("\nERR: Intersection does not exist.");
			buffer();
		}
		
		graph.displayReachableIntersections(id);
		buffer();
	}
	
	
	
	
	//
	//	ENTRY LOGIC
	//
	private void run() {
		System.out.println("City Navigation Map");
		
		while (true) {
			System.out.println("\n===== MENU =====");
			System.out.println("1) Add Intersection");
			System.out.println("2) Add Road");
			System.out.println("3) Remove Intersection");
			System.out.println("4) Remove Road");
			System.out.println("5) Display Map");
			System.out.println("6) Check Path Existence");
			System.out.println("7) List Reachable Intersections");
			System.out.println("8) Exit");
			System.out.print("\nType 'EXIT' at anytime to return to menu\n> ");
			
			String userInput = input.nextLine().trim();
			
			if (!userInput.matches("[1-8]")) {	//	Regex comparisons for simplicity
				System.out.println("\nERR: Please select a valid option");
				buffer();
			} else {
				int choice = Integer.parseInt(userInput);
				
				switch (choice) { 	// Main menu loop
				case 1:
					System.out.println("\n===== ADD INTERSECTION =====");
					addIntersection();
					break;
					
				case 2:
					System.out.println("\n===== ADD ROAD =====");
					addRoad();
					break;
					
				case 3:
					System.out.println("\n===== REMOVE INTERSECTION =====");
					removeIntersection();
					break;
					
				case 4:
					System.out.println("\n===== REMOVE ROAD =====");
					removeRoad();
					break;
					
				case 5:
					System.out.println("\n===== CITY MAP =====");
					displayMap();
					break;
					
				case 6:
					System.out.println("\n===== CHECK PATH EXISTENCE =====");
					checkPathExistence();
					break;
					
				case 7:
					System.out.println("\n===== LIST REACHABLE INTERSECTIONS =====");
					listReachableIntersections();
					break;
					
				case 8:
					input.close();
					System.out.println("\n===== EXITING PROGRAM =====");
					return; // Terminates
				}
			}
		}
	}
	
	
	
	
	//
	// 	PROGRAM STARTS HERE
	//
	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}
}
