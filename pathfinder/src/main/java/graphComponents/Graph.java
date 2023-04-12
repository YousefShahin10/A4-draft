package graphComponents;

import graphComponents.Edge;

import java.util.*;
import java.util.List;
//import pathfinder.src.main.java.graphComponents.Edge;
//import pathfinder.src.main.java.graphComponents.Node;

public class Graph extends GraphADT {
    //public neighbors = new HashMap<>();



    public Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        adjacencyList = new HashMap<Node, List<Node>>();
    }

    //@Override
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
    @Override
    public void createEdges(){
        System.out.println("hi");
    }
    public void createNodes(){
        System.out.println("hi");
    }
    // Helper method to get the edge between two nodes

}
