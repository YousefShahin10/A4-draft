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
    
    // Implementations of other GraphADT methods omitted for brevity

    @Override
    public double getDistance(Node source, Node dest) {
        // Use Dijkstra's algorithm to find the shortest path between the source and destination nodes
        Map<Node, Double> distances = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        queue.add(source);
        distances.put(source, 0.0);
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current == dest) {
                return distances.get(current);
            }
            
            for (Node neighbor : adjacencyList.get(current)) {
                Edge edge = getEdge(current, neighbor);
                double distance = distances.get(current) + edge.getWeight();
                if (!distances.containsKey(neighbor) || distance < distances.get(neighbor)) {
                    distances.put(neighbor, distance);
                    queue.add(neighbor);
                }
            }
        }

        // If no path is found between the source and destination nodes, return infinity
        return Double.POSITIVE_INFINITY;
    }

    // Helper method to get the edge between two nodes
    private Edge getEdge(Node source, Node dest) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(source) && edge.getDestination().equals(dest)) {
                return edge;
            }
        }
        return null;
    }
}
