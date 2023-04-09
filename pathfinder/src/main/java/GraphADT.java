import java.util.List;
import java.util.HashMap;

public abstract class GraphADT {
    protected List<Node> nodes;
    protected List<Edge> edges;
    protected HashMap<Node, List<Node>> adjacencyList;

    
    public abstract List<Node> getNodes();

    public abstract List<Edge> getEdges();

    public abstract void addNode(Node node);

    public abstract void removeNode(Node node);

    public abstract void addEdge(Edge edge);

    public abstract void removeEdge(Edge edge);

    public abstract List<Node> getNeighbors(Node node);
}