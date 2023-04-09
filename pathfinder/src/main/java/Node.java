import java.util.ArrayList;
import java.util.List;
public class Node {
    private int id;
    private String name;
    private double elevation;
    private final List<Edge> edges;

    public Node(int id, String name, double elevation) {
        this.id = id;
        this.name = name;
        this.elevation = elevation;
        this.edges = new ArrayList<Edge>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getElevation() {
        return elevation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }
    public List<Node> getNeighbors() {
        List<Node> neighbors = new ArrayList<Node>();
        for (Edge edge : edges) {
            neighbors.add(edge.getDestination());
        }
        return neighbors;
    }
    public double getDistance(Node neighbor) {
        for (Edge edge : edges) {
            if (edge.getDestination().equals(neighbor)) {
                return edge.getWeight();
            }
        }
        return Double.MAX_VALUE;
    }
}