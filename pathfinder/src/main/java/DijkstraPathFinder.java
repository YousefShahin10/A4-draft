import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class DijkstraPathFinder implements PathFinder {
    private Graph graph;

    public DijkstraPathFinder(Graph graph) {
        this.graph = graph;
    }

    @Override
    public List<Node> findPath(Node source, Node destination) {
        // Initialize distance map and visited set
        Set<Node> visited = new HashSet<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(graph.getNodes().size(), new NodeComparator());
        for (Node node : graph.getNodes()) {
            if (node == source) {
                node.setDistance(0);
            } else {
                node.setDistance(Double.POSITIVE_INFINITY);
            }
            queue.add(node);
        }

        // Perform Dijkstra's algorithm
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            visited.add(current);
            if (current == destination) {
                break;
            }
            for (Node neighbor : graph.getNeighbors(current)) {
                if (visited.contains(neighbor)) {
                    continue;
                }
                double distance = current.getDistance() + getEdgeWeight(current, neighbor);
                if (distance < neighbor.getDistance()) {
                    neighbor.setDistance(distance);
                    neighbor.setPrevious(current);
                    queue.remove(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        // Construct path from source to destination
        List<Node> path = new ArrayList<>();
        Node current = destination;
        while (current != null) {
            path.add(current);
            current = current.getPrevious();
        }
        Collections.reverse(path);

        return path;
    }

    private double getEdgeWeight(Node source, Node destination) {
        for (Edge edge : graph.getEdges()) {
            if (edge.getSource() == source && edge.getDestination() == destination) {
                return edge.getWeight();
            }
            if (!edge.isDirected() && edge.getSource() == destination && edge.getDestination() == source) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Edge not found: " + source + " -> " + destination);
    }

    private static class NodeComparator implements java.util.Comparator<Node> {
        @Override
        public int compare(Node a, Node b) {
            return Double.compare(a.getDistance(), b.getDistance());
        }
    }
}
