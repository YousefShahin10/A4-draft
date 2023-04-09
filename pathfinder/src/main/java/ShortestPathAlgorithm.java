import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ShortestPathAlgorithm implements PathFinder {
    private Map<Node, Double> distances;
    private Map<Node, Node> predecessors;

    public ShortestPathAlgorithm() {
        distances = new HashMap<Node, Double>();
        predecessors = new HashMap<Node, Node>();
    }

    @Override
    public List<Node> findPath(Node source, Node destination) {
        List<Node> path = new ArrayList<Node>();
        if (source.equals(destination)) {
            path.add(source);
            return path;
        }

        PriorityQueue<Node> unvisitedNodes = new PriorityQueue<Node>(Comparator.comparingDouble(distances::get));
        distances.put(source, 0.0);
        unvisitedNodes.add(source);

        while (!unvisitedNodes.isEmpty()) {
            Node current = unvisitedNodes.poll();

            if (current.equals(destination)) {
                while (predecessors.containsKey(current)) {
                    path.add(0, current);
                    current = predecessors.get(current);
                }
                path.add(0, current);
                break;
            }

            for (Node neighbor : current.getNeighbors()) {
                double distanceFromCurrent = current.getDistance(neighbor);
                double tentativeDistance = distances.get(current) + distanceFromCurrent;

                if (tentativeDistance < distances.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    distances.put(neighbor, tentativeDistance);
                    predecessors.put(neighbor, current);
                    unvisitedNodes.remove(neighbor);
                    unvisitedNodes.add(neighbor);
                }
            }
        }

        return path;
    }
}