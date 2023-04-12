import graphComponents.Edge;
import graphComponents.Node;
import org.junit.Test;
import static org.junit.Assert.*;

public class EdgeTest {

    @Test
    public void testGetId() {
        Node node1 = new Node(1, 0.0);
        Node node2 = new Node(2,  0.0);
        Edge edge = new Edge(1, node1, node2, 10.0);
        assertEquals(1, edge.getId());
    }

    @Test
    public void testGetSource() {
        Node node1 = new Node(1,  0.0);
        Node node2 = new Node(2,  0.0);
        Edge edge = new Edge(1, node1, node2, 10.0);
        assertEquals(node1, edge.getSource());
    }

    @Test
    public void testGetDestination() {
        Node node1 = new Node(1,  0.0);
        Node node2 = new Node(2, 0.0);
        Edge edge = new Edge(1, node1, node2, 10.0);
        assertEquals(node2, edge.getDestination());
    }

    @Test
    public void testGetWeight() {
        Node node1 = new Node(1, 0.0);
        Node node2 = new Node(2, 0.0);
        Edge edge = new Edge(1, node1, node2, 10.0);
        assertEquals(10.0, edge.getWeight(), 0.0001);
    }

    @Test
    public void testSetWeight() {
        Node node1 = new Node(1,  0.0);
        Node node2 = new Node(2, 0.0);
        Edge edge = new Edge(1, node1, node2, 10.0);
        edge.setWeight(5.0);
        assertEquals(5.0, edge.getWeight(), 0.0001);
    }

}
