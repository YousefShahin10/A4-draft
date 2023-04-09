import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class ShortestPathAlgorithmTest {

    private Graph graph;
    private Node nodeA, nodeB, nodeC, nodeD, nodeE;
    private Edge edgeAB, edgeAC, edgeBC, edgeBD, edgeCD, edgeCE, edgeDE;

    @Before
    public void setUp() throws Exception {
        // Create nodes
        nodeA = new Node(1,"A", 1.1);
        nodeB = new Node(2,"B", 1.1);
        nodeC = new Node(3,"C", 1.1);
        nodeD = new Node(4,"D", 1.1);
        nodeE = new Node(5,"E", 1.1);

        // Create edges
        edgeAB = new Edge(1, nodeA, nodeB, 4.0);
        edgeAC = new Edge(2, nodeA, nodeC, 2.0);
        edgeBC = new Edge(3, nodeB, nodeC, 1.0);
        edgeBD = new Edge(4, nodeB, nodeD, 5.0);
        edgeCD = new Edge(5, nodeC, nodeD, 8.0);
        edgeCE = new Edge(6, nodeC, nodeE, 10.0);
        edgeDE = new Edge(7, nodeD, nodeE, 2.0);

        // Create graph and add nodes/edges
        graph = new Graph();
        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addEdge(edgeAB);
        graph.addEdge(edgeAC);
        graph.addEdge(edgeBC);
        graph.addEdge(edgeBD);
        graph.addEdge(edgeCD);
        graph.addEdge(edgeCE);
        graph.addEdge(edgeDE);
    }
    @Test
    public void testFindPath() {
        PathFinder shortestPathAlgorithm = new ShortestPathAlgorithm();

        // Test path from node A to node E
        List<Node> pathAE = shortestPathAlgorithm.findPath(nodeA, nodeE);
        List<Node> expectedPathAE = Arrays.asList(nodeA, nodeC, nodeD, nodeE);
        assertEquals(expectedPathAE, pathAE);

        // Test path from node B to node E
        List<Node> pathBE = shortestPathAlgorithm.findPath(nodeB, nodeE);
        List<Node> expectedPathBE = Arrays.asList(nodeB, nodeC, nodeD, nodeE);
        assertEquals(expectedPathBE, pathBE);

        // Test path from node C to node E
        List<Node> pathCE = shortestPathAlgorithm.findPath(nodeC, nodeE);
        List<Node> expectedPathCE = Arrays.asList(nodeC, nodeD, nodeE);
        assertEquals(expectedPathCE, pathCE);

        // Test path from node A to node D
        List<Node> pathAD = shortestPathAlgorithm.findPath(nodeA, nodeD);
        List<Node> expectedPathAD = Arrays.asList(nodeA, nodeC, nodeD);
        assertEquals(expectedPathAD, pathAD);

        // Test path from node B to node D
        List<Node> pathBD = shortestPathAlgorithm.findPath(nodeB, nodeD);
        List<Node> expectedPathBD = Arrays.asList(nodeB, nodeC, nodeD);
        assertEquals(expectedPathBD, pathBD);

        // Test path from node C to node D
        List<Node> pathCD = shortestPathAlgorithm.findPath(nodeC, nodeD);
        List<Node> expectedPathCD = Arrays.asList(nodeC, nodeD);
        assertEquals(expectedPathCD, pathCD);
    /*@Test
    public void testShortestPathAlgorithm() {
        ShortestPathAlgorithm pathFinder = new ShortestPathAlgorithm();
        List<Node> path = pathFinder.findPath(nodeA, nodeC);
        List<Node> expectedPath = new ArrayList<>(Arrays.asList(nodeA, nodeB, nodeC));
        assertEquals(expectedPath, path);

        path = pathFinder.findPath(nodeA, nodeF);
        expectedPath = new ArrayList<>(Arrays.asList(nodeA, nodeB, nodeE, nodeF));
        assertEquals(expectedPath, path);

        path = pathFinder.findPath(nodeB, nodeD);
        expectedPath = new ArrayList<>(Arrays.asList(nodeB, nodeA, nodeD));
        assertEquals(expectedPath, path);

        path = pathFinder.findPath(nodeD, nodeC);
        expectedPath = new ArrayList<>(Arrays.asList(nodeD, nodeA, nodeB, nodeC));
        assertEquals(expectedPath, path);

        path = pathFinder.findPath(nodeE, nodeA);
        expectedPath = new ArrayList<>(Arrays.asList(nodeE, nodeB, nodeA));
        assertEquals(expectedPath, path);
    }

    @Test
    public void testShortestPathAlgorithmWithNoPath() {
        ShortestPathAlgorithm pathFinder = new ShortestPathAlgorithm();
        Node nodeG = new Node("G");
        List<Node> path = pathFinder.findPath(nodeA, nodeG);
        List<Node> expectedPath = new ArrayList<>();
        assertEquals(expectedPath, path);

        path = pathFinder.findPath(nodeG, nodeA);
        expectedPath = new ArrayList<>();
        assertEquals(expectedPath, path);*/
    }
}