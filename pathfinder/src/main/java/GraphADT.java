import java.util.List;

public abstract class GraphADT {
    
    public abstract List<Node> getNodes();

    public abstract List<Edge> getEdges();

    public abstract void addNode(Node node);

    public abstract void removeNode(Node node);

    public abstract void addEdge(Edge edge);

    public abstract void removeEdge(Edge edge);

    public abstract List<Node> getNeighbors(Node node);
}