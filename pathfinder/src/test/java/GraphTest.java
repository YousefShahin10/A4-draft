import graphComponents.Edge;
import graphComponents.Graph;
import graphComponents.Node;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Arrays;

public class GraphTest {

    @Test
    public void testAddNode() {
        Graph graph = new Graph();
        Node node1 = new Node(1,  0.0);
        Node node2 = new Node(2, 0.0);
        graph.addNode(node1);
        graph.addNode(node2);
        List<Node> nodes = graph.getNodes();
        assertTrue(nodes.contains(node1));
        assertTrue(nodes.contains(node2));
    }

    @Test
    public void testRemoveNode() {
        Graph graph = new Graph();
        Node node1 = new Node(1,  0.0);
        Node node2 = new Node(2,  0.0);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.removeNode(node1);
        List<Node> nodes = graph.getNodes();
        assertFalse(nodes.contains(node1));
        assertTrue(nodes.contains(node2));
    }

    @Test
    public void testAddEdge() {
        Graph graph = new Graph();
        Node node1 = new Node(1,  0.0);
        Node node2 = new Node(2,  0.0);
        Edge edge = new Edge(1, node1, node2, 5.0);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addEdge(edge);
        List<Edge> edges = graph.getEdges();
        assertTrue(edges.contains(edge));
    }

    @Test
    public void testRemoveEdge() {
        Graph graph = new Graph();
        Node node1 = new Node(1,  0.0);
        Node node2 = new Node(2, 0.0);
        Edge edge = new Edge(1, node1, node2, 5.0);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addEdge(edge);
        graph.removeEdge(edge);
        List<Edge> edges = graph.getEdges();
        assertFalse(edges.contains(edge));
    }

    @Test
    public void testGetNeighbors() {
        Graph graph = new Graph();
        Node node1 = new Node(1,  0.0);
        Node node2 = new Node(2,  0.0);
        Node node3 = new Node(3,  0.0);
        Edge edge1 = new Edge(1, node1, node2, 5.0);
        Edge edge2 = new Edge(2, node2, node3, 5.0);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        List<Node> neighbors = graph.getNeighbors(node2);
        List<Node> expectedNeighbors = Arrays.asList(node1, node3);
        assertEquals(expectedNeighbors, neighbors);
    }
}