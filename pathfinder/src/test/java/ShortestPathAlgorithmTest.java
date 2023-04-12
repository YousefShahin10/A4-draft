import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import algorithm.ShortestPathAlgorithm;
import graphComponents.Edge;
import graphComponents.Graph;
import graphComponents.Node;
import org.junit.Test;

public class ShortestPathAlgorithmTest {
    @Test
    public void testFindPath() {
        // Create a new graph
        Graph graph = new Graph();
        Node node1 = new Node(1,2.2);
        Node node2 = new Node(2,2.2);
        Node node3 = new Node(3,2.2);
        Node node4 = new Node(4,2.2);
        Node node5 = new Node(5,2.2);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);
        graph.addEdge(new Edge(1,node1, node2, 1.0));
        graph.addEdge(new Edge(2,node1, node3, 2.0));
        graph.addEdge(new Edge(3,node2, node4, 3.0));
        graph.addEdge(new Edge(4,node3, node4, 1.0));
        graph.addEdge(new Edge(5,node4, node5, 4.0));

        // Create a new algorithm.ShortestPathAlgorithm instance
        ShortestPathAlgorithm pathFinder = new ShortestPathAlgorithm(graph);

        // Test finding a path from node 1 to node 5
        List<Node> path = pathFinder.findPath(node1, node5);
        List<Node> expectedPath = Arrays.asList(node1, node3, node4, node5);
        assertEquals(expectedPath, path);


        // Test finding a path from node 1 to node 5
        List<Node> SecondPath = pathFinder.findPath(node3, node5);
        List<Node> SecondExpectedPath = Arrays.asList(node3, node4, node5);
        assertEquals(SecondExpectedPath, SecondPath);

        // Test finding a path from node 1 to node 5
        List<Node> ThirdPath = pathFinder.findPath(node5, node2);
        List<Node> FourthExpectedPath = Arrays.asList(node5, node4, node2);
        assertEquals(FourthExpectedPath, ThirdPath);
    }
}
