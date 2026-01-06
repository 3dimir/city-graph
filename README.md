# City Navigation Map System
A **Java CLI application** that implements a weighted graph data structure to model city intersections and roads.  
Users can **add/remove intersections and roads**, **check path existence between locations**, **view reachable destinations**, and **display the complete city map** through an interactive menu system.

---

## Features
- Custom graph implementation using adjacency list representation
- Weighted edges with optional distance values
- Support for both directed and undirected roads
- BFS traversal algorithm for pathfinding
- Comprehensive input validation and error handling
- Interactive CLI with "exit anytime" functionality
- Unit test suite with JUnit for graph operations
- Clean object-oriented design with Node, Edge, and Graph classes

---

## Examples

### Main Menu
```
City Navigation Map
===== MENU =====
1) Add Intersection
2) Add Road
3) Remove Intersection
4) Remove Road
5) Display Map
6) Check Path Existence
7) List Reachable Intersections
8) Exit

Type 'EXIT' at anytime to return to menu
>
```

### Adding an Intersection
```
>1
===== ADD INTERSECTION =====
Enter the intersection's ID:
>MainSt
Successfully added Intersection!
Press 'ENTER' to continue
```

### Adding a Road
```
>2
===== ADD ROAD =====
Select starting Intersection:
>MainSt
Select destination:
>ElmSt
Optional: Road distance (leave blank for default):
>5
Does the road go both ways?
Y/N >Y
Successfully added Road!
Press 'ENTER' to continue
```

### Displaying the Map
```
>5
===== CITY MAP =====
Intersection MainSt:
    -> Distance to ElmSt: (5)
    -> Distance to OakAve: (3)

Intersection ElmSt:
    -> Distance to MainSt: (5)
    -> Distance to PineRd: (7)

Intersection OakAve:
    -> Distance to MainSt: (3)
    -> Distance to PineRd: (2)

Intersection PineRd:
    -> Distance to ElmSt: (7)
    -> Distance to OakAve: (2)

Press 'ENTER' to continue
```

### Checking Path Existence
```
>6
===== CHECK PATH EXISTENCE =====
Select starting Intersection:
>MainSt
Select destination:
>PineRd
At least one path exists from MainSt to PineRd!
View?
Y/N >Y

Intersection MainSt reaches:
    -> ElmSt
    -> OakAve
    -> PineRd

Press 'ENTER' to continue
```

### Listing Reachable Intersections
```
>7
===== LIST REACHABLE INTERSECTIONS =====
Select Intersection:
>OakAve

Intersection OakAve reaches:
    -> MainSt
    -> ElmSt
    -> PineRd

Press 'ENTER' to continue
```

### Removing a Road
```
>4
===== REMOVE ROAD =====
Select starting Intersection:
>MainSt
Select destination:
>ElmSt
If applicable: Remove both directions?
Y/N >N
Successfully removed Road!
Press 'ENTER' to continue
```

### Removing an Intersection
```
>3
===== REMOVE INTERSECTION =====
Enter the intersection's ID:
>ElmSt
Successfully removed Intersection!
Press 'ENTER' to continue
```

---

## Technical Implementation

### Graph Structure
- **Node**: Represents an intersection with a unique ID
- **Edge**: Represents a road with a destination node and weight (distance)
- **Graph**: Adjacency list implementation using HashMap for O(1) node lookup

### Algorithms
- **BFS Traversal**: Used for pathfinding and finding all reachable intersections
- **Graph Manipulation**: Efficient add/remove operations with proper edge cleanup

### Data Structures Used
- HashMap for node storage (ID → Node mapping)
- HashMap for adjacency list (Node → List of Edges)
- LinkedList as queue for BFS traversal
- HashSet for tracking visited nodes

---

## Running the Program

1. Compile all Java files in the `city_nav_map` package
2. Run the `Main` class
3. Use the interactive menu to build and explore your city map

---

## Unit Tests

The project includes comprehensive JUnit tests covering:
- Adding/removing intersections
- Creating one-way and two-way roads
- Path existence checking
- Reachable intersection listing
- Edge case handling (isolated nodes, duplicate intersections)

Run `UnitTests.java` to verify all functionality.

---

### Author
Vladislav Lamakin  
lamakinvladislav@gmail.com  
https://github.com/3dimir
