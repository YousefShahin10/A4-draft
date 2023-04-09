import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
//import pathfinder.src.main.java.Edge;
//import pathfinder.src.main.java.Node;

public class Graph extends GraphADT{
    //public neighbors = new HashMap<>();



    public Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        adjacencyList = new HashMap<Node, List<Node>>();
    }
    @Override
    public List<Node> getNodes() {
        return nodes;
    }
    @Override
    public List<Edge> getEdges() {
        return edges;
    }
    @Override
    public void addNode(Node node) {
        nodes.add(node);
        adjacencyList.put(node, new ArrayList<Node>());
    }
    @Override
    public void removeNode(Node node) {
        nodes.remove(node);
        adjacencyList.remove(node);
        // Remove any edges involving this node
        List<Edge> toRemove = new ArrayList<Edge>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) || edge.getDestination().equals(node)) {
                toRemove.add(edge);
            }
        }
        edges.removeAll(toRemove);
    }
    @Override
    public void addEdge(Edge edge) {
        edges.add(edge);
        Node source = edge.getSource();
        Node destination = edge.getDestination();
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);
    }
    @Override
    public void removeEdge(Edge edge) {
        edges.remove(edge);
        Node source = edge.getSource();
        Node destination = edge.getDestination();
        adjacencyList.get(source).remove(destination);
        adjacencyList.get(destination).remove(source);
    }
    @Override
    public List<Node> getNeighbors(Node node) {
        return adjacencyList.get(node);
    }
}
