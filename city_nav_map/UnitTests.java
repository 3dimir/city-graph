package city_nav_map;

import static org.junit.Assert.*;
import org.junit.*;

public class UnitTests {
	
	private Graph graph;
	
	
	@Before
	public void setup() {
		graph = new Graph();
	}
	
	
	/**
	 * Tests if intersections can be added.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		graph.addIntersection("A");
		assertTrue(graph.hasNode("A"));
		assertFalse(graph.hasNode("B"));
	}
	
	
	/**
	 * Rules out duplicate intersections.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception {
		graph.addIntersection("A");
		graph.addIntersection("A");
		assertEquals(1, graph.getNodeCount());
	}
	
	
	/**
	 * Tests adding two-way roads.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {
		graph.addIntersection("A");
		graph.addIntersection("B");
		graph.addRoad("A", "B", 5, true);
		assertTrue(graph.hasRoad("A", "B"));
		assertTrue(graph.hasRoad("B", "A"));
	}
	
	
	/**
	 * Tests adding one-way roads.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception {
		graph.addIntersection("A");
		graph.addIntersection("B");
		graph.addRoad("A", "B", 5, false);
		assertTrue(graph.hasRoad("A", "B"));
		assertFalse(graph.hasRoad("B", "A"));
	}
	
	
	/**
	 * Tests removing two-way roads.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test5() throws Exception {
		graph.addIntersection("A");
		graph.addIntersection("B");
		graph.addRoad("A", "B", 5, true);
		
		graph.removeRoad("A", "B", true);
		assertFalse(graph.hasRoad("A", "B"));
		assertFalse(graph.hasRoad("B", "A"));
	}
	
	
	/**
	 * Tests removing one-way roads.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test6() throws Exception {
		graph.addIntersection("A");
		graph.addIntersection("B");
		graph.addRoad("A", "B", 5, true);
		
		graph.removeRoad("A", "B", false);
		assertFalse(graph.hasRoad("A", "B"));
		assertTrue(graph.hasRoad("B", "A"));
	}
	
	
	/**
	 * Displays the map.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test7() throws Exception {
		graph.addIntersection("A");
		graph.addIntersection("B");
		graph.addIntersection("C");
		graph.addIntersection("D");
		graph.addRoad("A", "B", 5, true);
		graph.addRoad("A", "C", 4, false);
		graph.addRoad("B", "C", 12, true);
		graph.addRoad("B", "D", 1, false);
		
		graph.displayMap();
	}
	
	
	/**
	 * Checks path existence from an intersection without any neighbors.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test8() throws Exception {
		graph.addIntersection("B");
		graph.addIntersection("D");
		graph.addRoad("B", "D", 1, false);
		
		assertFalse(graph.checkPathExists("D", "B"));
		assertEquals("[D]", graph.listReachableIntersections("D").toString());
	}
	
	
	/**
	 * Checks path existence from an intersection with neighbors.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test9() throws Exception {
		graph.addIntersection("A");
		graph.addIntersection("B");
		graph.addIntersection("C");
		graph.addIntersection("D");
		graph.addRoad("A", "B", 5, true);
		graph.addRoad("B", "C", 12, true);
		graph.addRoad("B", "D", 1, false);
		
		assertTrue(graph.checkPathExists("B", "A"));
		assertTrue(graph.checkPathExists("B", "C"));
		assertTrue(graph.checkPathExists("B", "D"));
		assertEquals("[A, B, C, D]", graph.listReachableIntersections("B").toString());
		graph.displayReachableIntersections("B");
	}
}
