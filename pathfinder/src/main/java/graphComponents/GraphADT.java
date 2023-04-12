package graphComponents;

import graphComponents.Edge;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public abstract class GraphADT {
    protected List<Node> nodes;
    protected List<Edge> edges;
    protected HashMap<Node, List<Node>> adjacencyList;


    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addNode(Node node) {
        nodes.add(node);
        adjacencyList.put(node, new ArrayList<Node>());
    }

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
    public void addEdge(Edge edge) {
        edges.add(edge);
        Node source = edge.getSource();
        Node destination = edge.getDestination();
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);
    }
    public void removeEdge(Edge edge) {
        edges.remove(edge);
        Node source = edge.getSource();
        Node destination = edge.getDestination();
        adjacencyList.get(source).remove(destination);
        adjacencyList.get(destination).remove(source);
    }
    public List<Node> getNeighbors(Node node) {
        return adjacencyList.get(node);
    }
    public Edge getEdge(Node source, Node dest) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(source) && edge.getDestination().equals(dest)) {
                return edge;
            }
            if (edge.getDestination().equals(source) && edge.getSource().equals(dest)) {
                return edge;
            }
        }
        return null;
    }

   // public abstract double getDistance(Node source, Node dest);
    public abstract void createEdges();
    public abstract void createNodes();

}