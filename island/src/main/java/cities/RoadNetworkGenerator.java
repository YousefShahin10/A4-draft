package cities;

import graphComponents.Graph;
import graphComponents.Node;
import cities.*;
import algorithm.*;
import meshcomponents.*;
import colors.IslandColors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class RoadNetworkGenerator {
    private Map<Node, Double> distances;
    private Map<Node, Node> predecessors;
    private Graph graph;
    private Node capital;
    private List<Node> cities;
    private Map<Node, Node> cityToCapital;
    private List<Node> path = new ArrayList<>();
    private List<MyVertex> verticesInPath = new ArrayList<>();

    public RoadNetworkGenerator(CityGraph graph, Node capital, List<Node> cities) {
        this.graph = graph;
        this.capital = capital;
        this.cities = cities;
    }

    private void createPathList(){
        for (Node c : cities){
            cityToCapital.put(c, capital);
        }
    }

    private void findShortestPath(CityGraph graph,Node start,Node destination, Map<MyVertex, Node> vertexToNodeMap){
        ShortestPathAlgorithm pathFinder = new ShortestPathAlgorithm(graph);
        for (Node c : cities) {
            path = pathFinder.findPath(start, destination);
        }
        for (Node n : path){
            // search for the vertex associated with the given node
            MyVertex vertex = null;
            for (Map.Entry<MyVertex, Node> entry : vertexToNodeMap.entrySet()) {
                if (entry.getValue().equals(n)) {
                    vertex = entry.getKey();
                    break;
                }
            }
            verticesInPath.add(vertex);
        }
        List<MySegment> segmentsInPath = new ArrayList<MySegment>();
        for (int i = 0; i < verticesInPath.size() - 1; i++) {
            MyVertex v1 = verticesInPath.get(i);
            MyVertex v2 = verticesInPath.get(i + 1);
            MySegment segment = new MySegment();
            segment.setColor(IslandColors.CITY);
            segment.setV1(v1);
            segment.setV2(v2);
            // set other properties of the segment if necessary
            segmentsInPath.add(segment);
        }
    }

    public void createNetwork(CityGraph graph, Node capital, List<Node> cities, Map<MyVertex, Node> vertexToNodeMap){
        for (Node n : cities){
            findShortestPath(graph,n,capital, vertexToNodeMap);
        }
    }


}

